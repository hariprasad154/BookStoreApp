package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.DTO.Verification;
import com.bookstoreapp.BookStoreApp.service.PasswordReset;
import com.bookstoreapp.BookStoreApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordResetController {
    @Autowired
    private PasswordReset passwordReset;
    @Autowired
    private UserService regstationService;
    @PostMapping("forgot")
    public String forgetPassword(@RequestParam String email){
        return passwordReset.forgotPassword(email);
    }
    @PutMapping( )
    public String resetPassword(@RequestParam String email,@RequestParam String password){
        return passwordReset.resetpassword(email,password);
    }
    @PutMapping("/varify")
    public ResponceDto validation(@RequestBody Verification validation ){
        return regstationService.varify(validation);
    }
}
