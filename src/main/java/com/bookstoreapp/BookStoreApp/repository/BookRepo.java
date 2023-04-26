package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<BookStore,Integer> {
    @Query(value = "SELECT * FROM bookstoreapp.book_store where name = :name",nativeQuery = true)
    List<BookStore> getBookByName(String name);
}
