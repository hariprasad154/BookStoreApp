package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.BookStore;

public interface BookService {
    ResponceDto addBook(BookStoreDto bookStoreDto);

    BookStore updateBook(int id ,BookStoreDto bookStoreDto);

    BookStore getById(int id);
}
