package com.agenda.domain.service;

import com.agenda.domain.exception.AtendimentoNaoEncontradoException;
import com.agenda.domain.model.Atendimento;
import com.agenda.domain.model.AtendimentoServicoValor;
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final AgendamentoService agendamentoService;
    private final ServicoPrestadoService servicoPrestadoService;
    private final UsuarioService usuarioService;

    public List<Atendimento> listar() {
        return atendimentoRepository.listar();
    }

    public Atendimento consultar(Long id) {
        return atendimentoRepository.findById(id)
             .orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
    }

    public Atendimento incluir(Atendimento atendimento) {
        atribuirEntidadesRelacionadas(atendimento);
        atendimento.setServicosValores(atribuirServicoValor(atendimento));
        atendimento.calcularValorTotal();
        return atendimentoRepository.save(atendimento);
    }

    private void atribuirEntidadesRelacionadas(Atendimento atendimento) {
        if (nonNull((atendimento.getAgendamento()))) {
            atendimento.setAgendamento(agendamentoService.consultar(atendimento.getAgendamento().getId()));
        }
        Usuario colaborador = usuarioService.consultar(atendimento.getColaborador().getId());
        atendimento.setColaborador(colaborador);
    }

    private Set<AtendimentoServicoValor> atribuirServicoValor(Atendimento atendimento) {
        atendimento.getServicosValores().forEach(item -> {
            if (nonNull(atendimento.getId())) {
                item.getId().setAtendimentoId(atendimento.getId());
            }
            item.setAtendimento(atendimento);
            item.setServicoPrestado(servicoPrestadoService.consultar(item.getId().getServicoPrestadoId()));
        });
        return atendimento.getServicosValores();
    }
}
