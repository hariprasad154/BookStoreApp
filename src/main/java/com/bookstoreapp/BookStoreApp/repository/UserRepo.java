package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends org.springframework.data.jpa.repository.JpaRepository<UserModel,Integer> {
    @Query(value = "select * from user_model where email= :email",nativeQuery = true)
    UserModel findEmail(String email);
//    @Query(value = "select * from user_model where email= :email",nativeQuery = true)
//    UserModel findIdByEmail(String email);
    @Query(value = "select password from user_model where email= :email",nativeQuery = true)
    String getPassword(String email);
    @Query(value = "select varify_otp from user_model where email= :email",nativeQuery = true)
    boolean getVarifyOtp(String email);
}
