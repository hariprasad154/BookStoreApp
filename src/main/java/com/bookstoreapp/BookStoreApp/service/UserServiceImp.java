package com.bookstoreapp.BookStoreApp.service;

import com.bookstoreapp.BookStoreApp.DTO.Login;
import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import com.bookstoreapp.BookStoreApp.DTO.UserDto;
import com.bookstoreapp.BookStoreApp.DTO.Verification;
import com.bookstoreapp.BookStoreApp.exception.CustomeException;
import com.bookstoreapp.BookStoreApp.model.UserModel;
import com.bookstoreapp.BookStoreApp.repository.UserRepo;
import com.bookstoreapp.BookStoreApp.util.EmailService;
import com.bookstoreapp.BookStoreApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailService emailService;



    @Override
    public ResponceDto login(Login login) {
        String email =login.getEmail();
        String password=login.getPassword();
        String varifyPassword=userRepo.getPassword(email);
        UserModel id=userRepo.findEmail(email);
        boolean varify=userRepo.getVarifyOtp(email);
        System.out.println("token result -"+varify);
        if (password.equals(varifyPassword) &&varify==true ){
            Optional<UserModel> data=userRepo.findById(id.getId());
            String token=jwtToken.createToken(id.getId());
            data.get().setToken(token);
            userRepo.save(data.get());
            return new ResponceDto("login successfull..... for ", "token :- "+token);
        }
        else{
            if(varifyPassword!=null &&varify == false){
                return new ResponceDto("The validation not done ","Validate the otp to login");
            }else {
                return new ResponceDto(" check the email and password", "The incorrect credentials");
            }
        }
    }
    @Override
    public ResponceDto varify(Verification verification) {
        String email=verification.getEmail();
        UserModel id =userRepo.findEmail(email);
        if(id!=null) {
            Optional<UserModel> data = userRepo.findById(id.getId());
            if (verification.getOtp() == data.get().getOtp()) {
                data.get().setVarifyOtp(true);
                userRepo.save(data.get());
                return new ResponceDto("The Varification done for email ", email);
            } else {
                return new ResponceDto("Varification not done check the mail ", "give correct data");
            }
        }else{
            return new ResponceDto("The email is not registerd ",email);
        }

         }

    @Override
    public ResponceDto register(UserDto userDto) {
        String email=userDto.getEmail();
        UserModel mail=userRepo.findEmail(email);
        if(mail!=null){
            return new ResponceDto("Enter the unique Email id ",userDto.getEmail());
        }else {
            UserModel userData=new UserModel(userDto);
            int genarateOtp=(int) ((Math.random() * 999999) % 899998) + 100001;
            userData.setOtp(genarateOtp);
            userRepo.save(userData);
            emailService.sendEmail(userData.getEmail(),"The is Register done OTP sent  ","hi....."+userData.getFirstName()+userData.getLastName() + "\n The OTP is "+genarateOtp + " ");
            return new ResponceDto("The data rigisterd succsusfully",userDto) ;
        }
    }

/*
The Curd opporations
 */
    @Override
    public UserModel getById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomeException(" Employee Not found .. wih id: "+ id));

    }
    @Override
    public List<UserModel> getAllData() {
        return userRepo.findAll();
    }
    @Override
    public UserModel UpdateEmployee(int id, UserDto userDto) {
        UserModel addressBookData =this.getById(id);
        addressBookData.updateData(userDto);
        return userRepo.save(addressBookData);
    }


    @Override
    public void delete(int id) {
        UserModel addressBookData =this.getById(id);
        userRepo.delete(addressBookData);
    }
    @Override
    public UserModel getdataByToken(String token) {
        int id= jwtToken.decodeToken(token);
        System.out.println(id+"id -------------");
        return userRepo.findById(id).orElseThrow(() -> new CustomeException("Employee Not found :- "+id));
    }

    @Override
    public String deletedataByToken(String token) {
        int id= jwtToken.decodeToken(token);
        UserModel userData =this.getById(id);
        userRepo.delete(userData);
        return "The data is deleted";
    }



}
