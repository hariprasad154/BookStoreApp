package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.Login;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.DTO.UserDto;
import com.bookstoreapp.BookStoreApp.DTO.Verification;
import com.bookstoreapp.BookStoreApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/adduser")
    public ResponceDto register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }
    @PutMapping("/varification")
    public ResponceDto validation(@RequestBody Verification verification ){
        return userService.varify(verification) ;
    }
    @PostMapping("/login")
    public ResponceDto login (@RequestBody Login login){
        return userService.login(login) ;
    }



}
