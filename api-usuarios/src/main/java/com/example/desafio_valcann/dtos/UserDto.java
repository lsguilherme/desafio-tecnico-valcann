package com.example.desafio_valcann.dtos;

import com.example.desafio_valcann.models.Role;

import java.time.LocalDateTime;

public record UserDto(Long id, String name, String email, Role role, Boolean isActive, LocalDateTime createdAt) {
}
