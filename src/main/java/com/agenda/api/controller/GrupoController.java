package com.agenda.api.controller;

import com.agenda.api.mapper.GrupoMapper;
import com.agenda.api.model.GrupoDTO;
import com.agenda.api.model.input.GrupoInputDTO;
import com.agenda.domain.model.Grupo;
import com.agenda.domain.service.GrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@RequiredArgsConstructor
public class GrupoController {
    private final GrupoService service;
    private final GrupoMapper mapper;

    @GetMapping
    public List<GrupoDTO> listar() {
        return mapper.toDto(service.listar());
    }

    @GetMapping("/{id}")
    public GrupoDTO obterPorId(@PathVariable Long id) {
        return mapper.toDto(service.obterPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO salvar(@RequestBody @Valid GrupoInputDTO input) {
        Grupo novoGrupo = mapper.toEntity(input);
        return mapper.toDto(service.salvar(novoGrupo));
    }

    @PutMapping("/{id}")
    public GrupoDTO atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInputDTO input) {
        Grupo grupo = mapper.toEntity(input);
        return mapper.toDto(service.atualizar(id, grupo));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
