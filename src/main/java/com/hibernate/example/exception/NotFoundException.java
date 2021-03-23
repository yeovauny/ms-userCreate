package com.hibernate.example.exception;

public class NotFoundException extends Exception {

    private String message;

    public NotFoundException(String message){
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
