package com.agenda.domain.service;

import com.agenda.domain.exception.EntidadeEmUsoException;
import com.agenda.domain.exception.GrupoNaoEncontradoException;
import com.agenda.domain.model.Grupo;
import com.agenda.domain.model.Permissao;
import com.agenda.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GrupoService {
    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
    private final GrupoRepository grupoRepository;
    private final PermissaoService permissaoService;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo obterPorId(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo atualizar(Long id, Grupo grupo) {
        Grupo grupoAtual = obterPorId(id);
        BeanUtils.copyProperties(grupo, grupoAtual, "id");
        return grupoAtual;
    }

    public void excluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    public void associarPermissao(Long grupoId, List<Long> permissoesIds) {
        Grupo grupo = obterPorId(grupoId);
        List<Permissao> permissoes = permissaoService.listarPorIds(permissoesIds);
        grupo.adicionarPermissoes(permissoes);
    }

    public void desassociarPermissao(Long grupoId, List<Long> permissoesIds) {
        Grupo grupo = obterPorId(grupoId);
        List<Permissao> permissoes = permissaoService.listarPorIds(permissoesIds);
        grupo.removerPermissao(permissoes);
    }
}
