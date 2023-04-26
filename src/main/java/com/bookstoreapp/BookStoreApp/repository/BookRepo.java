package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<BookStore,Integer> {
}
