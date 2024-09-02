package com.jiro.Spring_Web_Application.exceptions;

public class ResourceNotFoundException extends RuntimeException{

//    alt+insert->constructor with a message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
