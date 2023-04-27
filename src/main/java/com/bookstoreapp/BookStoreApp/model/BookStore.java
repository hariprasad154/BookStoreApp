package com.bookstoreapp.BookStoreApp.model;
import com.bookstoreapp.BookStoreApp.DTO.BookStoreDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class BookStore {
    @Id
    @GeneratedValue
    private int book_id;
    private String name;
    private String author;
    private String description;
    private String logo;
    private Float price;
    private int quantity;

    public void updateBook(BookStoreDto bookStoreDto){
        this.name=bookStoreDto.getName();
        this.author=bookStoreDto.getAuthor();
        this.description= bookStoreDto.getDescription();
        this.logo=bookStoreDto.getLogo();
        this.price= bookStoreDto.getPrice();
        this.quantity= bookStoreDto.getQuantity();
    }
    public BookStore(BookStoreDto bookStoreDto){
        this.updateBook(bookStoreDto);
    }
}