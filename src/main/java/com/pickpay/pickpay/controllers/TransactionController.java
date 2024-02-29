package com.pickpay.pickpay.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.pickpay.pickpay.dtos.TransactionDTO;
import com.pickpay.pickpay.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<Object> getTransaction(@RequestBody @Valid TransactionDTO transaction) throws Exception{     
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.createTrasaction(transaction));
    }
}
