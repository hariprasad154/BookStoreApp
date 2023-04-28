package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.CartDto;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.exception.CustomeException;
import com.bookstoreapp.BookStoreApp.model.BookStore;
import com.bookstoreapp.BookStoreApp.model.Cart;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.CartRepo;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp  implements  CartService{
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponceDto addCart(CartDto cartDto) {
        int user_id=jwtToken.decodeToken(cartDto.getToken());
        Optional<UserModel> user = userRepo.findById(user_id);//
        if(user.isPresent()) {
            Cart cart=cartRepo.findDataById(user_id);
//            Optional<Cart> cartData=cartRepo.findById(cart.getCart_id());
            if(cart!=null){
                int bookId=cart.getBookStore().getBook_id();
                System.out.println(bookId+"book id ");
                System.out.println(bookId+" ---------  data");
                if(cartDto.getBook_id()==bookId){
                    System.out.println("the Book is present in "+user_id);
                    Optional<Cart> data=cartRepo.findDataByBookId(cartDto.book_id);
                    System.out.println("the cart data is + "+data);
                    data.get().setQuantity(data.get().getQuantity()+cartDto.getQuantity());
                    return new ResponceDto("the cart is added ",cartRepo.save(data.get()));
                }else {
                    BookStore book = bookService.getById(cartDto.getBook_id());
                    Cart cartDta = new Cart(user.get(), book, cartDto.quantity);

                    return new ResponceDto("", cartRepo.save(cartDta));
            }
            }else {
                BookStore book = bookService.getById(cartDto.getBook_id());
                Cart cartDta = new Cart(user.get(), book, cartDto.quantity);
                cartRepo.save(cartDta);
                return new ResponceDto("", cartRepo.save(cartDta));
            }
        }
        else{
            return new ResponceDto("The cart is not added "," The data is not present with user ");
        }

    }

    @Override
    public ResponceDto removeCartById(int cartId) {
        cartRepo.deleteById(cartId);
        return new ResponceDto("The items deleted ","id"+cartId);
    }

    @Override
    public Cart getById(int cart_Id) {
        return cartRepo.findById(cart_Id).orElseThrow(() -> new CustomeException(" Data Not found .. wih id: "+ cart_Id));
    }
    @Override
    public ResponceDto getCartByToken(String token) {
        int id=jwtToken.decodeToken(token);
        System.out.println(id+"  id");
        int cartid=cartRepo.findIdByUserId(id);
        System.out.println(cartid +"cart id");
        if(cartid>0){
            return new ResponceDto("the data ",userRepo.findById(cartid));
        }else {
            return new ResponceDto("no data present with token ",null);
        }
//        Optional<UserModel> data = userRepo.findById(id);
//        if(data.isPresent()) {
//            List<Cart> cartList = cartRepo.findDataByToken(id);
//            return new ResponceDto("The data ", cartList);
//        }else {
//            return new ResponceDto("The data not found",id);
//        }
    }

    @Override
    public ResponceDto updateBytoken(String token,int cart_id,int quantity) {
        int id=jwtToken.decodeToken(token);
        System.out.println(id+"  id");
        int cartid=cartRepo.findIdByUserId(id);
        System.out.println(cartid+"  cart id");
        Optional<Cart> data=cartRepo.findById(cartid);
        if((data!=null)&&cartid==cart_id){
            data.get().setQuantity(quantity);
            return new ResponceDto("the qunatity Updated ",cartRepo.save(data.get()));
        }
        return null;
    }

    @Override
    public List<Cart> getAlldata() {
        return cartRepo.findAll();

    }
}
