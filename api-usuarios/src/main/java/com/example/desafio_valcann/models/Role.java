package com.example.desafio_valcann.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    MANAGER,
    ANALYST,
    ADMIN,
    VIEWER;

    @JsonValue
    public String toLowerCase(){
        return this.name().toLowerCase();
    }
}
