package com.example;

import java.util.UUID;

public record Account(UUID id, String name) {
    public Account {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }

}
