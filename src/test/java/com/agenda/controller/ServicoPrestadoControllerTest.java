package com.agenda.controller;

import com.agenda.api.controller.ServicoPrestadoController;
import com.agenda.api.mapper.ServicoPrestadoMapper;
import com.agenda.domain.service.ServicoPrestadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.agenda.common.ServicoPrestadoConstants.BASE_URI;
import static com.agenda.common.ServicoPrestadoConstants.SERVICO_PRESTADO;
import static com.agenda.common.ServicoPrestadoConstants.SERVICO_PRESTADO_DTO;
import static com.agenda.common.ServicoPrestadoConstants.SERVICO_PRESTADO_INPUT;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicoPrestadoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ServicoPrestadoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ServicoPrestadoService service;
    @MockBean
    private ServicoPrestadoMapper mapper;

    @Test
    void listarServicosPrestados_DeveRetornarStatus200() throws Exception {
        when(service.listar()).thenReturn(List.of(SERVICO_PRESTADO));
        when(mapper.toDto(List.of(SERVICO_PRESTADO))).thenReturn(List.of(SERVICO_PRESTADO_DTO));
        mockMvc.perform(get(BASE_URI))
             .andExpect(status().isOk())
             .andExpect(jsonPath("[0].id").value(SERVICO_PRESTADO_DTO.getId()))
             .andExpect(jsonPath("[0].nome").value(SERVICO_PRESTADO_DTO.getNome()));
    }

    @Test
    void consultarServicoPrestado_ComIdExistente_RetornarStatus200() throws Exception {
        when(service.consultar(1L)).thenReturn(SERVICO_PRESTADO);
        when(mapper.toDto(SERVICO_PRESTADO)).thenReturn(SERVICO_PRESTADO_DTO);
        mockMvc.perform(get(BASE_URI + "/{id}", 1))
             .andExpect(status().isOk())
             .andExpect(jsonPath("id").value(SERVICO_PRESTADO_DTO.getId()))
             .andExpect(jsonPath("nome").value(SERVICO_PRESTADO_DTO.getNome()));
    }

    @Test
    void incluirServicoPrestado_ComDadosValidos_RetornarStatus201() throws Exception {
        when(mapper.toEntity(SERVICO_PRESTADO_INPUT)).thenReturn(SERVICO_PRESTADO);
        doNothing().when(service).incluir(SERVICO_PRESTADO);
        mockMvc.perform(post(BASE_URI)
             .content(objectMapper.writeValueAsString(SERVICO_PRESTADO_INPUT))
             .contentType(APPLICATION_JSON))
             .andExpect(status().isCreated());
    }

    @Test
    void atualizarServicoPrestado_ComDadosValidos_RetornarStatus200() throws Exception {
        when(mapper.toEntity(SERVICO_PRESTADO_INPUT)).thenReturn(SERVICO_PRESTADO);
        doNothing().when(service).atualizar(1L, SERVICO_PRESTADO);
        mockMvc.perform(put(BASE_URI + "/{id}", 1)
             .content(objectMapper.writeValueAsString(SERVICO_PRESTADO_INPUT))
             .contentType(APPLICATION_JSON))
             .andExpect(status().isOk());
    }

    @Test
    void excluirServicoPrestado_ComIdExistente_RetornarStatus204() throws Exception {
        doNothing().when(service).excluir(1L);
        mockMvc.perform(delete(BASE_URI + "/{id}", 1))
             .andExpect(status().isNoContent());
    }
}
