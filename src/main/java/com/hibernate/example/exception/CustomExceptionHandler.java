package com.hibernate.example.exception;

import com.hibernate.example.exception.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleEventCustomException(Exception e, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("There are an Error ", details);
     return new ResponseEntity<>(error , HttpStatus.valueOf(500));
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleEventNotFoundException(Exception e, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("There are an Error ", details);
        return new ResponseEntity<>(error , HttpStatus.valueOf(404));
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleValidationError(Exception e){
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error= new ErrorResponse("Bad Request", details);
        return new ResponseEntity<>(error , HttpStatus.valueOf(400));
    }






}
