package com.bookstoreapp.BookStoreApp.DTO;
import lombok.Data;
@Data
public class BookStoreDto {
    private String name;
    private String author;
    private String description;
    private String logo;
    private Float price;
    private int quantity;
}