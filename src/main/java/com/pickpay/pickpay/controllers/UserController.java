package com.pickpay.pickpay.controllers;

import com.pickpay.pickpay.domains.user.UserModel;
import com.pickpay.pickpay.dtos.UserDTO;
import com.pickpay.pickpay.services.UserService;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping; 
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
    }
    

    @PostMapping()
    public ResponseEntity<Object> creatUser(@RequestBody @Valid UserDTO data) {
        UserModel newUser = new UserModel();
        BeanUtils.copyProperties(data, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(newUser));
    }
}
