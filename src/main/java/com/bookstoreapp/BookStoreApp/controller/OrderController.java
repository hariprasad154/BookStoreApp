package com.bookstoreapp.BookStoreApp.controller;

import com.bookstoreapp.BookStoreApp.DTO.OrderDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.service.OrderService;
import com.bookstoreapp.BookStoreApp.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponceDto placeOrder(@RequestBody OrderDto orderDto){
        return orderService.placeOrder(orderDto);
    }
    @GetMapping("/get/{order_id}")
    public ResponceDto getById(@PathVariable int order_id){
        return orderService.getById(order_id);
    }
    @GetMapping("/getAll")
    public ResponceDto getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/getbytoken/{token}")
    public ResponceDto getByToken(@PathVariable String token){
        return orderService.getByToken(token);
    }
    @PutMapping("/cancel")
    public String cancelTheOrder(@RequestParam String token , int order_id){
        return orderService.cancelOrder(token,order_id);
    }

}
