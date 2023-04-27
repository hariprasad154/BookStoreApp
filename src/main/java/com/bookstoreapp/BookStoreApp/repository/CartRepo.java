package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CartRepo extends org.springframework.data.jpa.repository.JpaRepository<Cart, Integer> {
    @Query(value = "select * from cart where cart_id = :cartId",nativeQuery = true)
    Object findDataById(int cartId);
//    @Query(value = "select * from cart where id = :id",nativeQuery = true)
//    List<Cart> findDataByToken(int id);
    @Query(value = "select cart_id from cart where id = :userId",nativeQuery = true)
    int findIdByUserId(int userId);
}