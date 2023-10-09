package com.agenda.domain.service;

import com.agenda.domain.exception.NegocioException;
import com.agenda.domain.exception.UsuarioNaoEncontradoException;
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

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario consultar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    public Usuario incluir(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent() && usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(String.format(USUARIO_JA_EXISTENTE, usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

}
