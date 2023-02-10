package com.agenda.api.controller;

import com.agenda.api.mapper.UsuarioMapper;
import com.agenda.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    private final UsuarioMapper mapper;
}
