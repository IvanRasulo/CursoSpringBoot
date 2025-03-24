package com.microcompany.productsservice.config;

import com.microcompany.productsservice.exception.GlobalProductException;
import com.microcompany.productsservice.exception.NewProductException;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlocalExceptionController {

    @ExceptionHandler(value = GlobalProductException.class)
    public ResponseEntity handleGlobalProductException(GlobalProductException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new StatusMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotfoundException ex) {
        return ResponseEntity.status(404).body(new StatusMessage(404, ex.getMessage()));
    }

    /*@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(value = NewProductException.class)
    public String handleNewProductException(NewProductException ex) {
        return ex.getMessage();
    }*/


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMismatchEx(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED.value()).body(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED.value()).body(new StatusMessage(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
