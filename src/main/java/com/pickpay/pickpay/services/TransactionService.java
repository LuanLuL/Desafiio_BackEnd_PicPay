package com.pickpay.pickpay.services;

import java.math.BigDecimal;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.pickpay.pickpay.domains.transaction.TransactionModel;
import com.pickpay.pickpay.domains.user.UserModel;
import com.pickpay.pickpay.dtos.TransactionDTO;
import com.pickpay.pickpay.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationServiceService;

    public TransactionModel createTrasaction(TransactionDTO transaction) throws Exception{
        UserModel sender = userService.findUserById(transaction.senderId());
        UserModel receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());
        if(!this.authorizeTransaction(sender, transaction.value())){
            throw new Exception("Transaction is not authorize");
        }
        
        var newTransaction = new TransactionModel();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transaction.value());
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationServiceService.sendNotification(sender, "Transação realizada para" + receiver.getName()+ " com sucesso!");
        this.notificationServiceService.sendNotification(sender, "Nova Transação recebida de" + sender.getName() + ".");

        return transactionRepository.save(newTransaction);
    }

    public boolean authorizeTransaction(UserModel sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }
        else return false;
    }
}
