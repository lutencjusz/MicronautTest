package com.example;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;

import java.util.Set;

@Controller("/accounts")
public class AccountController {

    private final AccountService accountService = new AccountService();

    @Get(produces = MediaType.APPLICATION_JSON)
    HttpResponse<Set<Account>> getAllAccounts() {
        return HttpResponse.ok(accountService.getAllAccounts());
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    HttpResponse<Account> addAccount(@Body Account account) {
        return HttpResponse.created(accountService.addAccount(account));
    }

    @Get("/{name}")
    Account getAccount(@Parameter String name) {
        return accountService.getAccountByName(name)
                .orElseThrow(() -> new HttpStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }
}
