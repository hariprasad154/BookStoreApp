package com.bookstoreapp.BookStoreApp.model;

import com.bookstoreapp.BookStoreApp.DTO.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders_table")
public class Order {
    @Id
    @GeneratedValue
    private int order_id;
    private LocalDate orderDate;
    private int quantity;

    private Float price;

    private String address;

    private int user_id;
    
    private float totalPrice;


//    private Float totalPrice=price*quantity;
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "book_id")
private BookStore bookStore;

private Boolean cancel = false;

//    public void update(OrderDto orderDto){
//        this.address=orderDto.getAddress();
//        this.orderDate=LocalDate.now();
//    }
//    public Order(OrderDto orderDto){
//        this.update(orderDto);
//    }
    public Order(int user_id,int quantity,BookStore bookStore,Float price,String address,float totalPrice,LocalDate orderDate){
        this.user_id=user_id;
        this.quantity=quantity;
        this.bookStore=bookStore;
        this.price=price;
        this.address=address;
        this.totalPrice=totalPrice;
        this.orderDate=orderDate;
    }
}

