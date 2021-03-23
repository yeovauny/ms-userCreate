package com.hibernate.example.exception;

public class CustomException  extends Exception{

    private String message;

    public CustomException(String message){
        this.message= message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
