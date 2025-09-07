package com.example.desafio_valcann.dtos;

import java.util.List;

public record UserPage (List<UserDto> data, PaginationInfo pagination) {
}
