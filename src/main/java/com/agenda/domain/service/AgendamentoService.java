package com.agenda.domain.service;

import com.agenda.api.model.AgendamentoEvento;
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

import static com.agenda.domain.util.Mensagens.MSG_AGENDAMENTO_EM_USO;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public List<AgendamentoEvento> listarEventoPorColaborador(Long colaboradorId) {
        return agendamentoRepository.listarEventoPorColaborador(colaboradorId);
    }

    public Agendamento consultar(Long id) {
        return agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }

    public Agendamento incluir(Agendamento agendamento) {
        atribuirEntidadesRelacionadas(agendamento);
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Long id, Agendamento agendamento) {
        Agendamento agendamentoAtual = consultar(id);
        atribuirEntidadesRelacionadas(agendamento);
        BeanUtils.copyProperties(agendamento, agendamentoAtual, "id", "dataCriacao");
        return agendamentoAtual;
    }

    private void atribuirEntidadesRelacionadas(Agendamento agendamento) {
        Cliente cliente = clienteService.consultar(agendamento.getCliente().getId());
        Usuario colaborador = usuarioService.consultar(agendamento.getColaborador().getId());
        agendamento.setCliente(cliente);
        agendamento.setColaborador(colaborador);
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
