package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
class DemoTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() {
        String response = client.toBlocking().retrieve("/accounts");
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("AccountController created"));
    }

    @Test
    void testAccountControllerResponseWithStatusCode200() {
        HttpResponse<Object> response = client.toBlocking().exchange("/accounts");
        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
    }
}
