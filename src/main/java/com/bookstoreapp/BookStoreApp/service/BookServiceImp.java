package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.exception.CustomeException;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.Cart;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.BookRepo;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserService userService;
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
    public BookStore updateBook(int book_id,BookStoreDto bookStoreDto) {
        BookStore bookStore=this.getById(book_id);
        bookStore.updateBook(bookStoreDto);
        return bookRepo.save(bookStore);
    }
    @Override
    public BookStore getById(int book_id) {
        return bookRepo.findById(book_id).orElseThrow(() -> new CustomeException(" Data Not found .. wih id: "+ book_id));
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
    public ResponceDto deleteById(int book_id) {
        BookStore book=this.getById(book_id);
        bookRepo.deleteById(book_id);
        return new ResponceDto("The Data has deleted",book_id);
    }

    @Override
    public String changeBookPrice(String token, int book_id, float price) {
        int useId=jwtToken.decodeToken(token);
//        UserModel data=userService.getById(useId);
        Optional<UserModel> user = userRepo.findById(useId);
//        System.out.println("id"+data);
        System.out.println("user "+user);
        if(user!=null){
            Optional<BookStore> bookStore=bookRepo.findById(book_id);
//            BookStore bookStore=this.getById(id);
            bookStore.get().setPrice(price);
            bookRepo.save(bookStore.get());
            return "Ther Price changed.........";
        }
        else {
            return "The Price Cant changed";
        }
    }
    @Override
    public String changeBookQuantity(String token, int book_id, int quantity) {
        int useId=jwtToken.decodeToken(token);
//        UserModel data=userService.getById(useId);
        Optional<UserModel> user = userRepo.findById(useId);
//        System.out.println("id"+data);
        System.out.println("user "+user);
        if(user!=null){
            Optional<BookStore> bookStore=bookRepo.findById(book_id);
//            BookStore bookStore=this.getById(book_id);
            bookStore.get().setQuantity(quantity);
            bookRepo.save(bookStore.get());
            return "Ther quantity changed.........";
        }
        else {
            return "The quantity Cant changed";
        }
    }
}
