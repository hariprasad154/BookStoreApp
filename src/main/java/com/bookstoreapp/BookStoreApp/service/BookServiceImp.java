package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.exception.CustomeException;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.BookRepo;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponceDto addBook(BookStoreDto bookStoreDto) {
        BookStore books=new BookStore(bookStoreDto);
        bookRepo.save(books);
        return new ResponceDto("The Book Detailes Added ",books) ;
    }

    @Override
    public BookStore updateBook(int id,BookStoreDto bookStoreDto) {
        BookStore bookStore=this.getById(id);
        bookStore.updateBook(bookStoreDto);
        return bookRepo.save(bookStore);
    }
    @Override
    public BookStore getById(int id) {
        return bookRepo.findById(id).orElseThrow(() -> new CustomeException(" Data Not found .. wih id: "+ id));
    }

    @Override
    public List<BookStore> getAllData() {
        return bookRepo.findAll();
    }

    @Override
    public List<BookStore> getBookByName(String name) {
        List<BookStore> books =bookRepo.getBookByName(name);
        for (Object x:books) {
            System.out.println("The books "+x);
        }
        return books;
    }

    @Override
    public ResponceDto deleteById(int id) {
        BookStore book=this.getById(id);
        bookRepo.deleteById(id);
        return new ResponceDto("The Data has deleted",id);
    }
}
