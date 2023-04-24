package com.bookstoreapp.BookStoreApp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$",message = "The Name is notEmpty")
    private String firstName;
    private String lastName;
    private String kyc;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate registeredDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedDate;
    @NotNull(message = "The email not be  null ")
    private String email;
    @NotNull(message = "The password not be  null ")
    private String password;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;
}
