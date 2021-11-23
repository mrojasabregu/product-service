package com.marketplace.product.controller;

import com.marketplace.product.exception.ProductExistException;
import com.marketplace.product.exception.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ProductExistException.class})
    public Map<String, String> handlerValidationException(ProductExistException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "Error: ";
        errors.put(fieldName, ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ProductNotExistException.class})
    public Map<String, String> handlerValidationException(ProductNotExistException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "Error: ";
        errors.put(fieldName, ex.getMessage());
        return errors;
    }
}
