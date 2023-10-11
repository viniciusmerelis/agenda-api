package com.agenda.service;

import com.agenda.domain.exception.EntidadeNaoEncontradaException;
import com.agenda.domain.exception.ServicoPrestadoNaoEncontradoException;
import com.agenda.domain.model.ServicoPrestado;
import com.agenda.domain.repository.ServicoPrestadoRepository;
import com.agenda.domain.service.ServicoPrestadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.agenda.common.ServicoPrestadoConstants.SERVICO_PRESTADO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicoPrestadoServiceTest {
    @InjectMocks
    private ServicoPrestadoService service;
    @Mock
    private ServicoPrestadoRepository repository;

    @Test
    public void listarServicosPrestados_RetornarTodosServicos() {
        when(repository.findAll()).thenReturn(List.of(SERVICO_PRESTADO));
        List<ServicoPrestado> sut = service.listar();
        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(SERVICO_PRESTADO);
    }

    @Test
    public void consultarServicoPrestado_ComIdExistente_RetornarServico() {
        when(repository.findById(1L)).thenReturn(Optional.of(SERVICO_PRESTADO));
        ServicoPrestado sut = service.consultar(1L);
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(SERVICO_PRESTADO);
    }

    @Test
    public void consultarServicoPrestado_ComIdInexistente_LancarServicoPrestadoNaoEncontradoException() {
        doThrow(new ServicoPrestadoNaoEncontradoException("")).when(repository).findById(99L);
        assertThatThrownBy(() -> service.consultar(99L)).isInstanceOf(EntidadeNaoEncontradaException.class);
    }

    @Test
    public void incluirServicoPrestado_ComDadosValidos() {
        service.incluir(SERVICO_PRESTADO);
        verify(repository, times(1)).save(SERVICO_PRESTADO);
    }

    @Test
    public void excluirServicoPrestado_ComIdExistente() {
        service.excluir(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
