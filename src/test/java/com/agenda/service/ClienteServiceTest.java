package com.agenda.service;

import com.agenda.domain.exception.ClienteNaoEncontradoException;
import com.agenda.domain.exception.EntidadeEmUsoException;
import com.agenda.domain.exception.EntidadeNaoEncontradaException;
import com.agenda.domain.model.Cliente;
import com.agenda.domain.repository.ClienteRepository;
import com.agenda.domain.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static com.agenda.common.ClienteConstants.CLIENTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @InjectMocks
    private ClienteService service;
    @Mock
    private ClienteRepository repository;

    @Test
    public void listarClientes_RetornarTodosClientes() {
        when(repository.findAll()).thenReturn(List.of(CLIENTE));
        List<Cliente> sut = service.listar();
        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(CLIENTE);
    }

    @Test
    public void consultarCliente_ComIdExistente_RetornarCliente() {
        when(repository.findById(1L)).thenReturn(Optional.of(CLIENTE));
        Cliente sut = service.consultar(1L);
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(CLIENTE);
    }

    @Test
    public void consultarCliente_ComIdInexistente_LancarClienteNaoEncontradoException() {
        doThrow(new ClienteNaoEncontradoException("")).when(repository).findById(99L);
        assertThatThrownBy(() -> service.consultar(99L)).isInstanceOf(EntidadeNaoEncontradaException.class);
    }

    @Test
    public void incluirCliente_ComDadosValidos() {
        service.incluir(CLIENTE);
        verify(repository, times(1)).save(CLIENTE);
    }

    @Test
    public void excluirCliente_ComIdExistente() {
        service.excluir(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void excluirCliente_ComIdInexistente_LancarClienteNaoEncontradoException() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(99L);
        assertThatThrownBy(() -> service.excluir(99L)).isInstanceOf(ClienteNaoEncontradoException.class);
    }

    @Test
    public void excluirCliente_ComIdEmUso_LancarEntidadeEmUsoException() {
        doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(1L);
        assertThatThrownBy(() -> service.excluir(1L)).isInstanceOf(EntidadeEmUsoException.class);
    }

}
