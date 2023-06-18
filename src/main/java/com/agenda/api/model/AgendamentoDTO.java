package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AgendamentoDTO {
    private Long id;
    private OffsetDateTime horario;
    private ClienteDTO cliente;
    private UsuarioDTO colaborador;
}
