package com.revature.team4.exceptions;

import javax.servlet.ServletException;

public class CustomException extends ServletException {
    public CustomException(String message) {
        super(message);
    }
}
