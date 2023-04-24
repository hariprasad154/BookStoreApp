package com.bookstoreapp.BookStoreApp.repository;

import com.bookstoreapp.BookStoreApp.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends org.springframework.data.jpa.repository.JpaRepository<UserModel,Integer> {
    @Query(value = "select email from user_model where email= :email",nativeQuery = true)
    String findEmail(String email);
    @Query(value = "select id from user_model where email= :email",nativeQuery = true)
    int findIdByEmail(String email);
    @Query(value = "select password from user_model where email= :email",nativeQuery = true)
    String getPassword(String email);
    @Query(value = "select token from user_model where email= :email",nativeQuery = true)
    String getVarifyOtp(String email);
}
