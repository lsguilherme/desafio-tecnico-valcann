package com.example.desafio_valcann.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record Issue(String message, HttpStatus statusCode, Date timestamp) {
}
