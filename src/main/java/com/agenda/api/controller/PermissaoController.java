package com.agenda.api.controller;

import com.agenda.api.mapper.PermissaoMapper;
import com.agenda.api.model.PermissaoDTO;
import com.agenda.domain.service.PermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permissoes")
@RequiredArgsConstructor
public class PermissaoController {
    private final PermissaoService service;
    private final PermissaoMapper mapper;

    @GetMapping
    public List<PermissaoDTO> listar() {
        return mapper.toDto(service.listar());
    }

    @GetMapping("/{id}")
    public PermissaoDTO obterPorId(@PathVariable Long id) {
        return mapper.toDto(service.buscar(id));
    }
}
