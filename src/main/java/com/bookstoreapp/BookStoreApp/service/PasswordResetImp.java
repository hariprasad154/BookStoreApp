package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.exception.CustomeException;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.EmailService;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetImp implements PasswordReset {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailService emailService;
    @Override
    public String forgotPassword(String email) {
        int id = userRepo.findIdByEmail(email);
        UserModel userData = userRepo.findById(id).orElseThrow(() -> new CustomeException(" Employee Not found .. wih id: " + id));
        Optional<UserModel> data = userRepo.findById(id);
        if (id <= 0) {
            return "The mail is Not Registered.....";
        } else {
            int genarateOtp = (int) ((Math.random() * 9999) % 8998) + 1001;
            data.get().setOtp(genarateOtp);
            data.get().setVarifyOtp(false);
            userRepo.save(data.get());
            emailService.sendEmail(userData.getEmail(), "The data added successfully ", "hi...." + userData.getFirstName()+userData.getLastName() + "\n your data added succsessfully " + "\n your otp is  <- " + genarateOtp + " ->");
            return "otp genarated sucsussfully -" + genarateOtp;
        }
    }

    @Override
    public String resetpassword(String email, String password) {
        String mail = userRepo.findEmail(email);
        if (mail==null){
            return "email is not present";
        }
        else{
            int id =userRepo.findIdByEmail(email);
            Optional<UserModel> data = userRepo.findById(id);
            if ((data.isPresent() && (data.get().isVarifyOtp() == true))) {
                data.get().setPassword(password);
                userRepo.save(data.get());
                return "The Password Reset Done";
            } else {
                return "password validaton not done becoze The Otp varification Not done  ~_~ :) ";
            }
        }
    }
}
