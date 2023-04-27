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
    @PutMapping("update/{book_id}")
    public BookStore updateBook(@PathVariable int book_id , @RequestBody BookStoreDto bookStoreDto){
        return bookService.updateBook(book_id,bookStoreDto);
    }
    @GetMapping("/{book_id}")
    public BookStore getById(@PathVariable int book_id){
        return bookService.getById(book_id);
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
    @DeleteMapping("/delete/{book_id}")
    public ResponceDto deleteDataById(@PathVariable int book_id){
        return bookService.deleteById(book_id);
    }

    @PutMapping("/changePrice/{token}")
    public String ChangeBookPrice(@PathVariable String token ,@RequestParam int book_id ,@RequestParam float price){
        return bookService.changeBookPrice(token,book_id,price);
    }
    @PutMapping("/changquantity/{token}")
    public String ChangeBookQuantity(@PathVariable String token ,@RequestParam int book_id ,@RequestParam int quantity){
        return bookService.changeBookQuantity(token,book_id,quantity);
    }
}
