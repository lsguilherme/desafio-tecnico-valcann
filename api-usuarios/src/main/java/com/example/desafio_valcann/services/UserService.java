package com.example.desafio_valcann.services;

import com.example.desafio_valcann.dtos.PaginationInfo;
import com.example.desafio_valcann.dtos.UserDto;
import com.example.desafio_valcann.dtos.UserPage;
import com.example.desafio_valcann.models.User;
import com.example.desafio_valcann.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPage findAllUsers(int page, int pageSize, String q, String role, Boolean isActive) {
        List<User> filteredUsers = userRepository.findAll().stream()
                .filter(u -> q==null || q.isBlank() || u.getName().toLowerCase().contains(q.toLowerCase()) ||
                        u.getEmail().toLowerCase().contains(q.toLowerCase())
                        )
                .filter(u -> role == null || role.isBlank() || u.getRole().name().equalsIgnoreCase(role))
                .filter(u -> isActive == null || u.getActive().equals(isActive))
                .toList();

        int size = Math.min(pageSize, 50);

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, filteredUsers.size());

        List<UserDto> pagedUsers = List.of();
        if (startIndex < endIndex) {
            pagedUsers = filteredUsers.subList(startIndex, endIndex)
                    .stream()
                    .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getRole(), u.getActive(), u.getCreatedAt()))
                    .toList();
        }

        long totalItems = filteredUsers.size();
        long totalPages = (long) Math.ceil((double) totalItems / size);

        PaginationInfo pagination = new PaginationInfo(page, size, totalItems, totalPages);
        return new UserPage(pagedUsers, pagination);
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getActive(), user.getCreatedAt());
    }
}
