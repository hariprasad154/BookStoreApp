package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/allbooks")
    public  ResponceDto getAllData(){
        List<BookStore> books=bookService.getAllData();
        return new ResponceDto("The all Books Present In Store",books);
    }
    @GetMapping("/bookname")
    public List<BookStore> getBookByName(@RequestParam String name){
        return bookService.getBookByName(name);
    }
    @DeleteMapping("/delete/{id}")
    public ResponceDto deleteDataById(@PathVariable int id){
        return bookService.deleteById(id);
    }

}
