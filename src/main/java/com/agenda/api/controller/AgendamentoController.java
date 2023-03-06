package com.agenda.api.controller;

import com.agenda.api.mapper.AgendamentoMapper;
import com.agenda.api.model.AgendamentoDTO;
import com.agenda.api.model.input.AgendamentoInput;
import com.agenda.api.model.input.ColaboradorMesInput;
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
    public List<AgendamentoDTO> listar(@RequestBody @Valid ColaboradorMesInput input) {
        return mapper.toDto(service.listar(input.getColaboradorId(), input.getMes()));
    }

    @GetMapping("/{id}")
    public AgendamentoDTO buscar(@PathVariable Long id) {
        return mapper.toDto(service.buscar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDTO salvar(@RequestBody @Valid AgendamentoInput input) {
        Agendamento agendamento = service.salvar(mapper.toEntity(input));
        return mapper.toDto(agendamento);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO atualizar(@PathVariable Long id, @RequestBody @Valid AgendamentoInput input) {
        Agendamento agendamento = service.atualizar(id, mapper.toEntity(input));
        return mapper.toDto(agendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
