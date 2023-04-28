package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends org.springframework.data.jpa.repository.JpaRepository<Cart, Integer> {
    @Query(value = "select * from cart where id = :userId",nativeQuery = true)
    Cart findDataById(int userId);
//    @Query(value = "select * from cart where id = :id",nativeQuery = true)
//    List<Cart> findDataByToken(int id);
    @Query(value = "select cart_id from cart where id = :userId",nativeQuery = true)
    int findIdByUserId(int userId);
    @Query(value = "select book_id from cart where id = :userId",nativeQuery = true)
    int findDataByUserId(int userId);
    @Query(value = "select * from cart where book_id = :bookId",nativeQuery = true)
    Optional<Cart> findDataByBookId(int bookId);
}