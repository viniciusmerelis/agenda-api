package com.agenda.common;

import com.agenda.api.model.UsuarioDTO;
import com.agenda.domain.model.Usuario;

import java.time.LocalDateTime;

public class UsuarioConstants {
    public static final Usuario USUARIO = new Usuario(1L, "Alice", "27994709408", "alicerita@colab.com.br", "123", LocalDateTime.now());
    public static final UsuarioDTO USUARIO_DTO = new UsuarioDTO(1L, "Alice", "27994709408");
}
