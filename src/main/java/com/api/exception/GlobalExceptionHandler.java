package com.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resorceNotFound(ResourceNotFoundException r,
                                                    WebRequest request){
        ErrorDTO error = new ErrorDTO(r.getMessage(), new Date(), request.getDescription(true));
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
