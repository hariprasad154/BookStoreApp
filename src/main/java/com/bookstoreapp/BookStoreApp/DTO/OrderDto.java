package com.bookstoreapp.BookStoreApp.DTO;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private String token;

    private String address;
}

