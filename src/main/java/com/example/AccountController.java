package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller("/accounts")
public class AccountController {
    private Map<UUID, Account> accountStore = new ConcurrentHashMap<>();

    @Get(produces = MediaType.TEXT_PLAIN)
    public String AccountController() {
        accountStore.put(UUID.randomUUID(), new Account(UUID.randomUUID(), "Test"));
        return "AccountController created: " + accountStore;
    }
}
