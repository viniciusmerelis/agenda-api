package com.agenda.controller;

import com.agenda.api.controller.AgendamentoController;
import com.agenda.api.mapper.AgendamentoMapper;
import com.agenda.domain.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.agenda.common.AgendamentoConstants.AGENDAMENTO;
import static com.agenda.common.AgendamentoConstants.AGENDAMENTO_DTO;
import static com.agenda.common.AgendamentoConstants.AGENDAMENTO_INPUT;
import static com.agenda.common.AgendamentoConstants.AGENDAMENTO_INPUT_INVALID;
import static com.agenda.common.AgendamentoConstants.BASE_URI;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AgendamentoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AgendamentoService service;
    @MockBean
    private AgendamentoMapper mapper;

    @Test
    public void consultarAgendamento_ComIdExistente_RetornarStatus200() throws Exception {
        when(service.consultar(1L)).thenReturn(AGENDAMENTO);
        when(mapper.toDto(AGENDAMENTO)).thenReturn(AGENDAMENTO_DTO);
        mockMvc.perform(get(BASE_URI + "/{id}", 1))
             .andExpect(status().isOk())
             .andExpect(jsonPath("id").value(AGENDAMENTO_DTO.getId()));
    }

    @Test
    public void incluirAgendamento_ComDadosValidos_RetornarStatus201() throws Exception {
        when(mapper.toEntity(AGENDAMENTO_INPUT)).thenReturn(AGENDAMENTO);
        when(service.incluir(AGENDAMENTO)).thenReturn(AGENDAMENTO);
        when(mapper.toDto(AGENDAMENTO)).thenReturn(AGENDAMENTO_DTO);
        mockMvc.perform(post(BASE_URI)
             .content(objectMapper.writeValueAsString(AGENDAMENTO_INPUT))
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isCreated());
    }

    @Test
    public void incluirAgendamento_ComDadosInvalidos_RetornarStatus400() throws Exception {
        when(mapper.toEntity(AGENDAMENTO_INPUT_INVALID)).thenReturn(AGENDAMENTO);
        mockMvc.perform(post(BASE_URI)
                  .content(objectMapper.writeValueAsString(AGENDAMENTO_INPUT_INVALID))
                  .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isBadRequest());
    }

    @Test
    public void atualizarAgendamento_ComDadosValidos_RetornarStatus200() throws Exception {
        when(mapper.toEntity(AGENDAMENTO_INPUT)).thenReturn(AGENDAMENTO);
        when(service.alterar(1L, AGENDAMENTO)).thenReturn(AGENDAMENTO);
        when(mapper.toDto(AGENDAMENTO)).thenReturn(AGENDAMENTO_DTO);
        mockMvc.perform(put(BASE_URI + "/{id}", 1)
             .content(objectMapper.writeValueAsString(AGENDAMENTO_INPUT))
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());
    }

    @Test
    public void excluirAgendamento_ComIdExistente_RetornarStatus204() throws Exception {
        doNothing().when(service).excluir(1L);
        mockMvc.perform(delete(BASE_URI + "/{id}", 1))
             .andExpect(status().isNoContent());
    }
}
