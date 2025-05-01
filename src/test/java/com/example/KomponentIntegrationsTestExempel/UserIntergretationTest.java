package com.example.KomponentIntegrationsTestExempel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
//Funkar kör på riktiga databasen

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntergretationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetUserByHttp() {
        User user = new User(null,"bill","@gmail.com");

        ResponseEntity<User> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/user", user, User.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Long userId = postResponse.getBody().getId();

        ResponseEntity <User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/user/" + userId, User.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("bill", getResponse.getBody().getName());
        assertEquals("@gmail.com", getResponse.getBody().getEmail());

    }



}