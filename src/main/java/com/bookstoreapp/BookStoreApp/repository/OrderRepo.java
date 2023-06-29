package com.bookstoreapp.BookStoreApp.repository;
import com.bookstoreapp.BookStoreApp.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepo extends org.springframework.data.jpa.repository.JpaRepository<Order, Integer>{
    @Query(value = "SELECT * FROM bookstoreapp.orders_table where user_id = :userId ",nativeQuery = true)
    List<Order> findByUserId(int userId);

//    @Query(value = "SELECT * FROM bookstoreapp.orders_table where user_id = :userId and order_id=orderId ",nativeQuery = true)
//    Optional<Order> FindByOrderIdIdUserId(int userId, int orderId);
}
