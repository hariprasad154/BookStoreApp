package com.bookstoreapp.BookStoreApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Long cart_id;

    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserModel userModel;

    @JsonIgnoreProperties
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookStore bookStore;

    private int quantity;



    public Cart(UserModel userModel , BookStore bookStore, int quantity) {
        this.userModel = userModel;
        this.bookStore = bookStore;
        this.quantity = quantity;
    }

}
