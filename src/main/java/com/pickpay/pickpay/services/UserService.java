package com.pickpay.pickpay.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pickpay.pickpay.domains.user.UserModel;
import com.pickpay.pickpay.domains.user.UserModelType;
import com.pickpay.pickpay.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(UserModel sender, BigDecimal amount) throws Exception {
        if(sender.getType() == UserModelType.MERCHANT){
            throw new Exception("Merchant Users doesn't do transaction");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("There is not enough balance");
        }
    }

    public UserModel findUserById(UUID id) throws Exception {
        Optional<UserModel> user = this.userRepository.findById(id);
        if(!user.isPresent()){
           throw new Exception("User didn't find");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(user.get(), userModel);
        return userModel;
    }

    public List<UserModel> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public UserModel saveUser(UserModel user){
        return this.userRepository.save(user);
    }
}
