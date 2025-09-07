package com.example.desafio_valcann.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Issue> handleNotFoundException(UserNotFoundException e){
        Issue issue = new Issue(e.getMessage(), HttpStatus.NOT_FOUND, new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(issue);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Issue> handleInternalServerError(Exception e){
        Issue issue = new Issue("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(issue);
    }
}
