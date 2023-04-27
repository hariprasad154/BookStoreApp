package com.bookstoreapp.BookStoreApp.model;

import com.bookstoreapp.BookStoreApp.DTO.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;

    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;

    private String email;
    private String password;
    private String token;
    private int otp;
    private boolean varifyOtp;
    private LocalDate purchaseDate;
//    private LocalDate expiryDate;

    public void updateData(UserDto userDto){
        this.firstName=userDto.getFirstName();
        this.lastName=userDto.getLastName();
        this.dob=userDto.getDob();
        this.email=userDto.getEmail();
//        this.expiryDate=userDto.getExpiryDate();
        this.registeredDate=LocalDate.now();
//        this.purchaseDate=userDto.getPurchaseDate();
//        this.kyc=userDto.getKyc();
        this.updatedDate=userDto.getUpdatedDate();
        this.password=userDto.getPassword();
    }
    public UserModel(UserDto userDto){
        this.updateData(userDto);
    }
}
