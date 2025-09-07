package com.example.desafio_valcann.repositories;

import com.example.desafio_valcann.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        ClassPathResource resource = new ClassPathResource("mock-users.json");

        try {
            this.users = mapper.readValue(resource.getInputStream(), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar o arquivo mock-users.json", e);
        }
    }


    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }
}
