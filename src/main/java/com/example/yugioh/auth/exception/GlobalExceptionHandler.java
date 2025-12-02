package com.example.yugioh.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public void handleUserAlreadyExists(UserAlreadyExistsException ex) {
        log.error(ex.getMessage());


    }

    // Handle generic login errors from AuthenticationManager
    @ExceptionHandler({AuthenticationException.class, IllegalArgumentException.class})
    public void handleAuthenticationError(Exception ex) {
        log.error(ex.getMessage());
    }
}
