package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.Login;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.DTO.UserDto;
import com.bookstoreapp.BookStoreApp.DTO.Verification;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserModel> getAllData() ;

    ResponceDto login(Login login) ;

    ResponceDto varify(Verification verification) ;

    ResponceDto register(UserDto userDto);

    UserModel getById(int id);

    UserModel UpdateEmployee(int id, UserDto userDto);

    void delete(int id);

    UserModel getdataByToken(String token);

    String deletedataByToken(String token);


}
