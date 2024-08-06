package com.example;

import java.util.*;

public class AccountService {
    private Set<Account> accounts = new HashSet<>();

    public AccountService() {
        accounts.add(new Account(UUID.randomUUID(), "Test_name_1", "200"));
        accounts.add(new Account(UUID.randomUUID(), "Test name 2", "300"));
    }

    public Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public Account addAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public Optional<Account> getAccountByName(String name) {
        return Optional.ofNullable(accounts.stream()
                .filter(account -> account.name().equals(name))
                .findFirst()
                .orElse(null));
    }
}
