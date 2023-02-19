package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {
    private Long id;
    private LocalDateTime horario;
    private ClienteDTO cliente;
    private UsuarioDTO colaborador;
    private String servico;
}
