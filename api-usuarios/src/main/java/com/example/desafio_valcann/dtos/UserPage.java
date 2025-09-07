package com.example.desafio_valcann.dtos;

import com.example.desafio_valcann.models.User;

import java.util.List;

public record UserPage (List<User> data, PaginationInfo pagination) {
}
