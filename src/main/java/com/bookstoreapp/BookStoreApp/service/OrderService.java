package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.OrderDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;

public interface OrderService {

    ResponceDto placeOrder(OrderDto orderDto);


    ResponceDto getById(int orderId);

    ResponceDto getAllOrders();

    ResponceDto getByToken(String token);

    String cancelOrder(String token, int orderId);
}
