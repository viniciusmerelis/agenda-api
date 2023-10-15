package com.agenda.controller;

import com.agenda.api.controller.ClienteController;
import com.agenda.api.mapper.ClienteMapper;
import com.agenda.domain.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static com.agenda.common.ClienteConstants.BASE_URI;
import static com.agenda.common.ClienteConstants.CLIENTE;
import static com.agenda.common.ClienteConstants.CLIENTE_DTO;
import static com.agenda.common.ClienteConstants.CLIENTE_INPUT;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ClienteController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClienteService service;
    @MockBean
    private ClienteMapper mapper;

    @BeforeAll
    public void beforeAll() {
        mockMvc(mockMvc);
        container.start();
    }

    @AfterAll
    public void afterAll() {
        container.stop();
    }

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

    @Test
    public void listarClientes_DeveRetornarStatus200() {
        when(service.listar()).thenReturn(List.of(CLIENTE));
        when(mapper.toDto(List.of(CLIENTE))).thenReturn(List.of(CLIENTE_DTO));
        given()
         .when()
             .get(BASE_URI)
         .then()
             .statusCode(200)
             .body("$", hasSize(1))
             .assertThat(jsonPath("[0].id").value(CLIENTE_DTO.getId()))
             .assertThat(jsonPath("[0].nome").value(CLIENTE_DTO.getNome()))
             .assertThat(jsonPath("[0].telefone").value(CLIENTE_DTO.getTelefone()));
    }

    @Test
    void consultarCliente_QuandoExistirId_RetornarStatus200() {
        when(service.consultar(1L)).thenReturn(CLIENTE);
        when(mapper.toDto(CLIENTE)).thenReturn(CLIENTE_DTO);
        given()
        .when()
             .get(BASE_URI + "/{id}", 1)
        .then()
             .statusCode(200)
             .assertThat(jsonPath("id").value(CLIENTE_DTO.getId()))
             .assertThat(jsonPath("nome").value(CLIENTE_DTO.getNome()))
             .assertThat(jsonPath("telefone").value(CLIENTE_DTO.getTelefone()));
    }

    @Test
    void incluirCliente_ComDadosValidos_RetornarStatus201() throws Exception {
        when(mapper.toEntity(CLIENTE_INPUT)).thenReturn(CLIENTE);
        doNothing().when(service).incluir(CLIENTE);
        given()
             .contentType(ContentType.JSON)
             .body(objectMapper.writeValueAsString(CLIENTE_INPUT))
        .when()
             .post(BASE_URI)
        .then()
             .statusCode(201);
    }

    @Test
    void atualizarCliente_ComDadosValidos_RetornarStatus200() throws Exception {
        when(mapper.toEntity(CLIENTE_INPUT)).thenReturn(CLIENTE);
        doNothing().when(service).atualizar(1L, CLIENTE);
        given()
             .contentType(ContentType.JSON)
             .body(objectMapper.writeValueAsString(CLIENTE_INPUT))
        .when()
             .put(BASE_URI + "/{id}", 1)
        .then()
             .statusCode(200);
    }

    @Test
    void excluirCliente_ComIdExistente_RetornarStatus204() {
        doNothing().when(service).excluir(1L);
        given()
        .when()
             .delete(BASE_URI + "/{id}", 1)
        .then()
             .statusCode(204);
    }
}
