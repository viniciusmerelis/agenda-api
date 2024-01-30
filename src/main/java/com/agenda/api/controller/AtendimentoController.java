package com.agenda.api.controller;

import com.agenda.api.mapper.AtendimentoMapper;
import com.agenda.api.model.AtendimentoDTO;
import com.agenda.api.model.input.AtendimentoInput;
import com.agenda.domain.model.Atendimento;
import com.agenda.domain.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoService service;
    private final AtendimentoMapper mapper;

    @GetMapping
    public List<AtendimentoDTO> listar() {
        return mapper.toDto(service.listar() );
    }

    @GetMapping("/{id}")
    public AtendimentoDTO buscar(@PathVariable Long id) {
        return mapper.toDto(service.consultar(id));
    }

    @PostMapping
    public AtendimentoDTO incluir(@RequestBody @Valid AtendimentoInput input) {
        Atendimento atendimento = service.incluir(mapper.toEntity(input));
        return mapper.toDto(atendimento);
    }
}
