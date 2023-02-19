package com.agenda.domain.service;

import com.agenda.domain.exception.AgendamentoNaoEncontradoException;
import com.agenda.domain.exception.EntidadeEmUsoException;
import com.agenda.domain.model.Agendamento;
import com.agenda.domain.model.Cliente;
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendamentoService {
    private static final String MSG_AGENDAMENTO_EM_USO = "Esse agendamento não pode ser excluirdo, pois está em uso";
    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public List<Agendamento> listar(Long colaboradorId, Long mes) {
        return agendamentoRepository.listarPorColaboradorEMes(colaboradorId, mes);
    }

    public Agendamento buscar(Long id) {
        return agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }

    public Agendamento salvar(Agendamento agendamento) {
        Cliente cliente = clienteService.buscarReferencia(agendamento.getCliente().getId());
        Usuario colaborador = usuarioService.buscarReferencia(agendamento.getColaborador().getId());
        agendamento.setCliente(cliente);
        agendamento.setColaborador(colaborador);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Long id, Agendamento agendamento) {
        Agendamento agendamentoAtual = buscar(id);
        Cliente cliente = clienteService.buscarReferencia(agendamento.getCliente().getId());
        Usuario colaborador = usuarioService.buscarReferencia(agendamento.getColaborador().getId());
        agendamento.setCliente(cliente);
        agendamento.setColaborador(colaborador);
        BeanUtils.copyProperties(agendamento, agendamentoAtual, "id", "dataCriacao");
        return agendamentoAtual;
    }

    public void excluir(Long id) {
        try {
            agendamentoRepository.deleteById(id);
            agendamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AgendamentoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(MSG_AGENDAMENTO_EM_USO);
        }
    }
}
