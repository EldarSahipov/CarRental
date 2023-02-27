package com.example.carrental.controller.valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
}
