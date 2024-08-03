package com.example;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable.Serializable
@Serdeable.Deserializable
public record Account(UUID id, String name, String amount) {}
