package com.agenda.domain.service;

import com.agenda.domain.exception.AtendimentoNaoEncontradoException;
import com.agenda.domain.model.Atendimento;
import com.agenda.domain.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final AgendamentoService agendamentoService;
    private final UsuarioService usuarioService;

    public List<Atendimento> listar() {
        return atendimentoRepository.findAll();
    }

    public Atendimento consultar(Long id) {
        return atendimentoRepository.findById(id)
             .orElseThrow(() -> new AtendimentoNaoEncontradoException(id));
    }
}
