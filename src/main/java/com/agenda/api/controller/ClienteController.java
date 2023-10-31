package com.agenda.api.controller;

import com.agenda.api.mapper.ClienteMapper;
import com.agenda.api.model.ClienteDTO;
import com.agenda.api.model.input.ClienteInput;
import com.agenda.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;
    private final ClienteMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<ClienteDTO> listar() {
        return mapper.toDto(service.listar());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ClienteDTO buscar(@PathVariable Long id) {
        return mapper.toDto(service.consultar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid ClienteInput input) {
        service.incluir(mapper.toEntity(input));
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ClienteInput input) {
        service.atualizar(id, mapper.toEntity(input));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
