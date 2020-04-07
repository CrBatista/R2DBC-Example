package com.crbatista.integration;

import com.crbatista.entity.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getGreetingWithStoredIdShouldReturn() {
        final String url = "http://localhost:" + port + "/crbatista/greeting?id=1";

        final ResponseEntity<Greeting> response = this.restTemplate.getForEntity(url, Greeting.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("GreetingMessageExample", response.getBody().getMessage());
}

    @Test
    public void getGreetingWithNonStoredIdShouldNotReturnBody() {
        final String url = "http://localhost:" + port + "/crbatista/greeting?id=999";

        final ResponseEntity<Greeting> response = this.restTemplate.getForEntity(url, Greeting.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
