package com.agenda.api.controller;

import com.agenda.api.mapper.ServicoPrestadoMapper;
import com.agenda.api.model.ServicoPrestadoDTO;
import com.agenda.api.model.input.ServicoPrestadoInput;
import com.agenda.domain.service.ServicoPrestadoService;
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
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
    private final ServicoPrestadoService service;
    private final ServicoPrestadoMapper mapper;

    @GetMapping
    public List<ServicoPrestadoDTO> listar() {
        return mapper.toDto(service.listar());
    }

    @GetMapping("/{id}")
    public ServicoPrestadoDTO buscar(@PathVariable Long id) {
        return mapper.toDto(service.consultar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid ServicoPrestadoInput input) {
        service.incluir(mapper.toEntity(input));
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ServicoPrestadoInput input) {
        service.atualizar(id, mapper.toEntity(input));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
