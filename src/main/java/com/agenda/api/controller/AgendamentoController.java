package com.agenda.api.controller;

import com.agenda.api.mapper.AgendamentoMapper;
import com.agenda.api.model.AgendamentoDTO;
import com.agenda.api.model.AgendamentoEvento;
import com.agenda.api.model.input.AgendamentoInput;
import com.agenda.domain.model.Agendamento;
import com.agenda.domain.service.AgendamentoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;
    private final AgendamentoMapper mapper;

    @GetMapping
    public List<AgendamentoEvento> listarEventoPorColaborador(@RequestParam Long colaboradorId) {
        return service.consultarEventoPorColaborador(colaboradorId);
    }

    @GetMapping("/{id}")
    public AgendamentoDTO consultar(@PathVariable Long id) {
        return mapper.toDto(service.consultar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDTO incluir(@RequestBody @Valid AgendamentoInput input) {
        Agendamento agendamento = service.incluir(mapper.toEntity(input));
        return mapper.toDto(agendamento);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO atualizar(@PathVariable Long id, @RequestBody @Valid AgendamentoInput input) {
        Agendamento agendamento = service.alterar(id, mapper.toEntity(input));
        return mapper.toDto(agendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
