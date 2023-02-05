package com.agenda.domain.service;

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

    public Permissao obterPorId(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada."));
    }
}
