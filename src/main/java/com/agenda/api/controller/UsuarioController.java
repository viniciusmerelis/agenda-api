package com.agenda.api.controller;

import com.agenda.api.mapper.UsuarioMapper;
import com.agenda.api.model.UsuarioDTO;
import com.agenda.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return mapper.toDto(service.listar());
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscar(@PathVariable Long id) {
        return mapper.toDto(service.consultar(id));
    }
}
