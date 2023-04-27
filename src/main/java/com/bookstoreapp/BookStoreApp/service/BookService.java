package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.BookStore;

import java.util.List;

public interface BookService {
    ResponceDto addBook(BookStoreDto bookStoreDto);

    BookStore updateBook(int book_id ,BookStoreDto bookStoreDto);

    BookStore getById(int book_id);

    List<BookStore> getAllData();

    List<BookStore> getBookByName(String name);

    ResponceDto deleteById(int book_id);

    String changeBookPrice(String token, int book_id, float price);

    String changeBookQuantity(String token, int book_id, int quantity);
}
