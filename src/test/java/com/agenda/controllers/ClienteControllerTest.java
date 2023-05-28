package com.agenda.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class ClienteControllerTest {

    @LocalServerPort
    private int port;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:11.5")
            .withDatabaseName("agenda")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.flyway.url=", container::getJdbcUrl);
        registry.add("spring.flyway.user=", container::getPassword);
        registry.add("spring.flyway.password=", container::getUsername);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void retornarStatus201_quandoCriarCliente() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "\t\"nome\": \"Fulana\",\n" +
                        "\t\"telefone\": \"27992696999\"\n" +
                        "}")
                .when()
                .post("/api/clientes")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
