package com.example.desafio_valcann.controllers;

import com.example.desafio_valcann.dtos.UserDto;
import com.example.desafio_valcann.dtos.UserPage;
import com.example.desafio_valcann.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "API REST simples para listagem de usuários.\n\n" +
        "Funcionalidades:\n\n" +
        "- Listar usuários de forma paginada com filtros a partir de um .json.\n\n" +
        "- Buscar um usuário por ID.")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Listar usuários paginados",
            description = "Busca usuários pelo nome ou email com filtros opcionais",
            parameters = {
                @Parameter(
                        name = "page",
                        description = "Número da página."
                ),
                @Parameter(
                        name = "pageSize",
                        description = "Quantidade de itens por página.\n\n" +
                        "- Máximo de itens por página = 50."
                ),
                @Parameter(
                        name = "q",
                        description = "Filtro por nome ou email do usuário",
                        example = "Gabriela"
                ),
                @Parameter(
                        name = "role",
                        description = "Filtro por função do usuário",
                        schema = @Schema(
                                type = "string",
                                allowableValues = {"manager", "analyst", "admin", "viewer"},
                                description = "Função no sistema"
                        )
                ),
            @Parameter(
                    name = "is_active",
                    description = "Filtro por status ativo do usuário."
            )
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<UserPage> findAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Boolean is_active
    ){
        UserPage response = userService.findAllUsers(page, pageSize, q, role, is_active);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar usuário por ID", 
            description = "Retorna um usuário específico pelo seu ID",
            parameters = {
            @Parameter(
                    name = "id",
                    description = "ID do usuário.",
                    required = true
            )
    }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        UserDto user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
}
