package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class DemoTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testAccountControllerGetAllAccountsAsString() {
        String response = client.toBlocking().retrieve("/accounts");
        assertNotNull(response);
        assertTrue(response.contains("Test_name_1"));
        System.out.println(response);
    }

    @Test
    void testAccountControllerGetAllAccountsAsHttpResponse() {
        HttpResponse<Object> response = client.toBlocking().exchange("/accounts");
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void testAccountControllerAddAccount() {
        UUID id = UUID.randomUUID();
        HttpResponse<Object> response = client.toBlocking().exchange(HttpRequest.POST("/accounts", new Account(id, "Test_name_3", "400")));
        assertEquals(HttpStatus.CREATED, response.getStatus());
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getBody(Account.class).get().amount()).as("Sprawdzenie kwoty").containsOnlyOnce("400");
        softAssertions.assertThat(response.getBody(Account.class).get().name()).as("Sprawdzenie nazwy").containsOnlyOnce("Test_name_3");
        softAssertions.assertThat(response.getBody(Account.class).get().id().toString()).as("Sprawdzenie id").containsOnlyOnce(id.toString());
        softAssertions.assertThat(response.getBody(Account.class).get().name()).as("Sprawdzenie nazwy").isNotBlank();
        softAssertions.assertThat(response.getBody(Account.class).get().name()).as("Sprawdzenie nazwy").isNotEmpty();
        softAssertions.assertAll();
    }

    @Test
    void testAccountControllerGetAccountNegative() {
        try {
            client.toBlocking().exchange(HttpRequest.GET("/accounts/notfound"));
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }

    @Test
    void testAccountControllerGetAccountPositive() {
        HttpResponse<Account> response = client.toBlocking().exchange(HttpRequest.GET("/accounts/Test_name_1"), Account.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Test_name_1", response.body().name());
    }
}
