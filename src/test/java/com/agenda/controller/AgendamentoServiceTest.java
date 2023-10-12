package com.agenda.controller;

import com.agenda.api.model.AgendamentoEvento;
import com.agenda.common.ClienteConstants;
import com.agenda.common.UsuarioConstants;
import com.agenda.domain.exception.AgendamentoNaoEncontradoException;
import com.agenda.domain.exception.EntidadeEmUsoException;
import com.agenda.domain.exception.EntidadeNaoEncontradaException;
import com.agenda.domain.model.Agendamento;
import com.agenda.domain.repository.AgendamentoRepository;
import com.agenda.domain.service.AgendamentoService;
import com.agenda.domain.service.ClienteService;
import com.agenda.domain.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static com.agenda.common.AgendamentoConstants.AGENDAMENTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService service;
    @Mock
    private AgendamentoRepository repository;
    @Mock
    private ClienteService clienteService;
    @Mock
    private UsuarioService usuarioService;

    @Test
    public void listarAgendamentos_PorIdDoColaboradorValido_RetornarAgendamentos() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        AgendamentoEvento projection = factory.createProjection(AgendamentoEvento.class);
        LocalDateTime date = LocalDateTime.of(2023, Month.JANUARY, 1, 8, 0);
        projection.setId(1L);
        projection.setTitle("Evento");
        projection.setDate(date);
        when(repository.consultarEventoPorColaborador(1L)).thenReturn(List.of(projection));
        List<AgendamentoEvento> sut = service.consultarEventoPorColaborador(1L);
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(projection);
        assertThat(sut.get(0).getId()).isEqualTo(1);
        assertThat(sut.get(0).getTitle()).isEqualTo("Evento");
        assertThat(sut.get(0).getDate()).isEqualTo(date);
    }

    @Test
    public void consultarAgendamento_ComIdExistente_RetornarAgendamento() {
        when(repository.consultar(1L)).thenReturn(Optional.of(AGENDAMENTO));
        Agendamento sut = service.consultar(1L);
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(AGENDAMENTO);
    }

    @Test
    public void consultarAgendamento_ComIdInexistente_LancarAgendamentoNaoEncontradoException() {
        doThrow(new AgendamentoNaoEncontradoException("")).when(repository).consultar(99L);
        assertThatThrownBy(() -> service.consultar(99L)).isInstanceOf(EntidadeNaoEncontradaException.class);
    }

    @Test
    public void incluirAgendamento_ComDadosValidos_RetornarAgendamento() {
        when(clienteService.consultar(1L)).thenReturn(ClienteConstants.CLIENTE);
        when(usuarioService.consultar(1L)).thenReturn(UsuarioConstants.USUARIO);
        when(repository.save(AGENDAMENTO)).thenReturn(AGENDAMENTO);
        Agendamento sut = service.incluir(AGENDAMENTO);
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(AGENDAMENTO);
    }

    @Test
    public void excluirAgendamento_ComIdExistente() {
        service.excluir(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void excluirAgendamento_ComIdInexistente_LancarAgendamentoNaoEncontradoException() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(99L);
        assertThatThrownBy(() -> service.excluir(99L)).isInstanceOf(AgendamentoNaoEncontradoException.class);
    }

    @Test
    public void excluirAgendamento_ComIdEmUso_LancarEntidadeEmUsoException() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(1L);
        assertThatThrownBy(() -> service.excluir(1L)).isInstanceOf(EntidadeEmUsoException.class);
    }
}
