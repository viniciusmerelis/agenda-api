package com.agenda.domain.service;

import com.agenda.domain.exception.PermissaoNaoEncontradaException;
import com.agenda.domain.model.Permissao;
import com.agenda.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    public List<Permissao> listarPorIds(List<Long> ids) {
        return permissaoRepository.findAllById(ids);
    }

    public Permissao obterPorId(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(id));
    }
}
