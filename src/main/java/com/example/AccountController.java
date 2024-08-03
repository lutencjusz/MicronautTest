package com.example;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

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

    @Get("/{id}")
    HttpResponse<Account> getAccount(@Parameter String id) {
        return HttpResponse.ok(accountService.getAccount(id));
    }
}
