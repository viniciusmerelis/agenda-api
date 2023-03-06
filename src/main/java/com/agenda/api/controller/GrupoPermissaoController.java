package com.agenda.api.controller;

import com.agenda.api.mapper.PermissaoMapper;
import com.agenda.api.model.PermissaoDTO;
import com.agenda.api.model.input.PermissaoIdInput;
import com.agenda.domain.model.Grupo;
import com.agenda.domain.model.Permissao;
import com.agenda.domain.service.GrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grupos/{grupoId}/permissoes")
@RequiredArgsConstructor
public class GrupoPermissaoController {
    private final GrupoService grupoService;
    private final PermissaoMapper permissaoMapper;

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscar(grupoId);
        return permissaoMapper.toDto((List<Permissao>) grupo.getPermissoes());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@RequestBody @Valid PermissaoIdInput input, @PathVariable Long grupoId) {
        grupoService.associarPermissao(grupoId, input.getPermissoesIds());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@RequestBody @Valid PermissaoIdInput input, @PathVariable Long grupoId) {
        grupoService.desassociarPermissao(grupoId, input.getPermissoesIds());
    }
}
