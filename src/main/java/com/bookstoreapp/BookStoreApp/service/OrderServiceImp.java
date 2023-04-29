package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.OrderDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.Cart;
import com.bookstoreapp.BookStoreApp.model.Order;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.CartRepo;
import com.bookstoreapp.BookStoreApp.repository.OrderRepo;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.EmailService;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private BookService bookService;
//    private Order order;


    @Override
    public ResponceDto placeOrder(OrderDto orderDto) {
        int user_id= jwtToken.decodeToken(orderDto.getToken());
        List<Cart> cartList=cartRepo.findAllDta(user_id);
        Optional<UserModel> userData=userRepo.findById(user_id);
        List<Order> ordersPlaced=new ArrayList<>();
        System.out.println("!!!!!!!!!!!!!!!!!");
        System.out.println("the cart list  "+cartList);
        for(int i=0;i<cartList.size();i++){
            int bookId=cartList.get(i).getBookStore().getBook_id();
            BookStore book = bookService.getById(bookId);
            float price=cartList.get(i).getBookStore().getPrice();
            int quantity=cartList.get(i).getQuantity();
            float totalPrice=price*quantity;
            LocalDate date=LocalDate.now();
            Order placeOrder=new Order(user_id,quantity,book,price,orderDto.getAddress(),totalPrice,date);
            ordersPlaced.add(placeOrder);
            orderRepo.save(placeOrder);
        }
        emailService.sendEmail(userData.get().getEmail(),"The order is Succusfully placed Mr."+userData.get().getFirstName(),"The order Detailes are given "+ordersPlaced);
        return new ResponceDto("The Orders Placed is for the user ",ordersPlaced);
    }

    @Override
    public ResponceDto getById(int orderId) {
        return new ResponceDto("The Data for the order id ",orderRepo.findById(orderId));
    }

    @Override
    public ResponceDto getAllOrders() {
        return new ResponceDto("The oder Data",orderRepo.findAll());
    }

    @Override
    public ResponceDto getByToken(String token) {
        int userId=jwtToken.decodeToken(token);
        List<Order> userOrders=orderRepo.findByUserId(userId);
        return new ResponceDto("the orders related to user",userOrders);
    }

    @Override
    public String cancelOrder(String token, int orderId) {
        int userId=jwtToken.decodeToken(token);
        Optional<Order> orderData=orderRepo.findById(orderId);
        System.out.println("--------------------\n"+orderData);
        if(orderData!=null) {
            if (orderData.get().getUser_id() == userId) {
                orderData.get().setCancel(true);
                orderRepo.save(orderData.get());
                return "The Cancelation done ";
            } else {
                return "The Cancelation not done check the userid ";
            }
        }return "The Order id is not present";

    }

}
