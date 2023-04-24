package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.DTO.UserDto;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersDataController {
    List<UserModel> list=new ArrayList<>();

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponceDto getDataByid(@PathVariable int id){
        UserModel addressBookData = userService.getById(id);
        ResponceDto responceDto = new ResponceDto("Data is",addressBookData);
        return responceDto;
    }
    @GetMapping("/")
    public ResponceDto getAllData(){
        List<UserModel> data=userService.getAllData();
        ResponceDto responceDto =new ResponceDto("The All Employees ",data);
        return responceDto;
    }
    @PutMapping("/update/{id}")
    public ResponceDto update(@RequestBody UserDto userDto, @PathVariable int id){
        UserModel addressBookData =userService.UpdateEmployee(id,userDto);
        ResponceDto responceDto = new ResponceDto("Data is",addressBookData);
        return responceDto;
    }
    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable int id){
        userService.delete(id);
        return "Deleted the data from the id"+id;
    }
    @GetMapping("/token/{token}")
    public ResponseEntity<ResponceDto> getDataByToken(@PathVariable String token){
        UserModel userData=userService.getdataByToken(token);
        ResponceDto responceDto = new ResponceDto("Data for the token is-",userData);

        return new ResponseEntity<>(responceDto, HttpStatus.CREATED);
    }
    @DeleteMapping("/deletetoken/{token}")
    public ResponseEntity<ResponceDto> deleteDataByToken(@PathVariable String token){
        String userData=userService.deletedataByToken(token);
        ResponceDto responceDto = new ResponceDto("Data is deleted",userData);
        return new ResponseEntity<>(responceDto, HttpStatus.CREATED);
    }
}
