package com.example.desafio_valcann.services;

import com.example.desafio_valcann.dtos.UserDto;
import com.example.desafio_valcann.dtos.UserPage;
import com.example.desafio_valcann.exceptions.UserNotFoundException;
import com.example.desafio_valcann.models.Role;
import com.example.desafio_valcann.models.User;
import com.example.desafio_valcann.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User(1L, "João Silva", "joao@email.com", Role.ADMIN, true, LocalDateTime.now());
        userDto = new UserDto(1L, "João Silva", "joao@email.com", Role.ADMIN, true, LocalDateTime.now());
    }

    @Test
    @DisplayName("Deve retornar usuário quando ID existir")
    void getUserById_WhenUserExists_ShouldReturnUserDto(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDto result = userService.findUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("João Silva", result.name());
        assertEquals("joao@email.com", result.email());
    }

    @Test
    @DisplayName("Deve retornar exceção quando usuário ID não existir")
    void getUserById_WhenUserNotExists_ShouldThrowException(){
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserById(999L));
    }

    @Test
    @DisplayName("Deve retornar usuários páginados quando buscar todos usuários")
    void getAllUsers_ShouldReturnPaginatedUsers(){
        when(userRepository.findAll()).thenReturn(List.of(user));

        UserPage result = userService.findAllUsers(1, 10, null, null, null);

        assertNotNull(result);
        assertEquals(1, result.data().size());
        assertEquals(1, result.pagination().page());
        assertEquals(10, result.pagination().pageSize());
        assertEquals(1, result.pagination().totalItems());
    }
}