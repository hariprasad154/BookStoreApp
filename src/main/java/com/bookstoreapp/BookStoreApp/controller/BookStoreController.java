package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookStoreController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponceDto addBook(@RequestBody BookStoreDto bookStoreDto){
        return bookService.addBook(bookStoreDto);
    }
    @PutMapping("update/{id}")
    public BookStore updateBook(@PathVariable int id , @RequestBody BookStoreDto bookStoreDto){
        return bookService.updateBook(id,bookStoreDto);
    }
    @GetMapping("/{id}")
    public BookStore getById(@PathVariable int id){
        return bookService.getById(id);
    }

}
