package com.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AccountService {
    private Set<Account> accounts = new HashSet<>();

    public AccountService() {
        accounts.add(new Account(UUID.randomUUID(), "Test name 1", "200"));
        accounts.add(new Account(UUID.randomUUID(), "Test name 2", "300"));
    }

    public Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public Account addAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public Account getAccount(String id) {
        try {
            return accounts.stream()
                    .filter(account -> account.id().toString().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        } catch (IllegalArgumentException e) {
            System.out.println("getAccount: " + e.getMessage());
            return new Account(null, null, null);
        }
    }
}
