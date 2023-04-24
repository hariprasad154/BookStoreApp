package com.bookstoreapp.BookStoreApp.exception;

import com.bookstoreapp.BookStoreApp.DTO.ResponceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookStoreAppException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponceDto> handleMethodMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage=errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponceDto responceDto=new ResponceDto("Exception While handling Rest Api call ,",errorMessage);
        return  new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);
        //What ever the responce getting by using the Responce entity we are getting that
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponceDto> HttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ResponceDto responceDto=new ResponceDto("The Date shoud be in dd-mm-yyyy formate",exception.getMessage());
        return new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomeException.class)
    public ResponseEntity<ResponceDto> handleEmployeeException(CustomeException exception){
        ResponceDto responceDto=new ResponceDto("Exception While handling Rest Api call ,",exception.getMessage());
        return new ResponseEntity<>(responceDto, HttpStatus.BAD_REQUEST);
    }
}
