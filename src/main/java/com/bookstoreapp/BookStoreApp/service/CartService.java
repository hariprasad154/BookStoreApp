package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.CartDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.Cart;

import java.util.List;

public interface CartService {
    ResponceDto addCart(CartDto cartDto);

    ResponceDto removeCartById(int cartId);

    Cart getById(int cartId);

    ResponceDto getCartByToken(String token);

    ResponceDto updateBytoken(String token,int cart_id,int quantity);

    List<Cart> getAlldata();
}
