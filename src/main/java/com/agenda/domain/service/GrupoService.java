package com.agenda.domain.service;

import com.agenda.domain.model.Grupo;
import com.agenda.domain.model.Permissao;
import com.agenda.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GrupoService {
    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
    private final GrupoRepository grupoRepository;
    private final PermissaoService permissaoService;

    public Grupo obterPorId(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado."));
    }

    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void excluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Grupo não encontrado.");

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = obterPorId(grupoId);
        Permissao permissao = permissaoService.obterPorId(permissaoId);
        grupo.adicionarPermissao(permissao);
    }

    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = obterPorId(grupoId);
        Permissao permissao = permissaoService.obterPorId(permissaoId);
        grupo.removerPermissao(permissao);
    }
}
