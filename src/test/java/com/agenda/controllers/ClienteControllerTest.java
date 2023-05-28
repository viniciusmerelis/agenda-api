package com.agenda.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ClienteControllerTest {

    private static final String FILE_BASE_PATH = "src/test/resources/payloads/cliente";
    private static final String BASE_URI = "/api/clientes";

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
        registry.add("spring.flyway.url", container::getJdbcUrl);
        registry.add("spring.flyway.user", container::getPassword);
        registry.add("spring.flyway.password", container::getUsername);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void retornaStatus200_quandoListarClientes() {
        given()
             .contentType(ContentType.JSON)
             .when()
             .get(BASE_URI)
             .then()
             .statusCode(200);
    }

    @Test
    void retornaStatus200_quandoObterClientePorId() {
        given()
             .contentType(ContentType.JSON)
             .pathParam("id", 1)
             .when()
             .get(BASE_URI + "/{id}")
             .then()
             .statusCode(200);
    }

    @Test
    void retornarStatus201_quandoCriarCliente() {
        File json = new File(FILE_BASE_PATH + "/ClienteInput-Create.json");
        given()
             .contentType(ContentType.JSON)
             .body(json)
             .when()
             .post(BASE_URI)
             .then()
             .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void retornarStatus200_quandoAtualizarCliente() {
        File json = new File(FILE_BASE_PATH + "/ClienteInput-Update.json");
        given()
             .contentType(ContentType.JSON)
             .pathParam("id", 1)
             .body(json)
             .when()
             .put(BASE_URI + "/{id}")
             .then()
             .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void retornarStatus204_quandoExcluirCliente() {
        given()
             .contentType(ContentType.JSON)
             .pathParam("id", 6)
             .when()
             .delete(BASE_URI + "/{id}")
             .then()
             .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
