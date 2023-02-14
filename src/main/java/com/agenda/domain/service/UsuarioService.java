package com.agenda.domain.service;

import com.agenda.domain.exception.NegocioException;
import com.agenda.domain.exception.UsuarioNaoEncontradoException;
import com.agenda.domain.model.Grupo;
import com.agenda.domain.model.Usuario;
import com.agenda.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    private static final String USUARIO_JA_EXISTENTE = "Já existe um usuário cadastrado com o e-mail: %s";
    private final UsuarioRepository usuarioRepository;
    private final GrupoService grupoService;

    public List<Usuario> listarPorGrupo(Long grupoId) {
        return usuarioRepository.listarPorGrupo(grupoId);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    public Usuario salvar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format(USUARIO_JA_EXISTENTE, usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    public void associarGrupos(Long usuarioId, List<Long> gruposIds) {
        Usuario usuario = buscar(usuarioId);
        List<Grupo> grupos = grupoService.listarPorIds(gruposIds);
        usuario.adicionarGrupos(grupos);
    }

    public void desassociarGrupos(Long usuarioId, List<Long> gruposIds) {
        Usuario usuario = buscar(usuarioId);
        List<Grupo> grupos = grupoService.listarPorIds(gruposIds);
        usuario.removerGrupos(grupos);
    }
}
