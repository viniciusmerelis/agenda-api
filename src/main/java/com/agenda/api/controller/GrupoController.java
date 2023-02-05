package com.agenda.api.controller;

import com.agenda.api.mapper.GrupoMapper;
import com.agenda.api.model.GrupoDTO;
import com.agenda.domain.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grupos")
@RequiredArgsConstructor
public class GrupoController {
    private final GrupoService service;
    private final GrupoMapper mapper;

    @GetMapping("/{id}")
    public GrupoDTO obterPorId(@PathVariable Long id) {
        return mapper.toDto(service.obterPorId(id));
    }
}
