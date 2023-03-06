package com.agenda.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoInput {
    @NotNull
    private LocalDateTime horario;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long colaboradorId;

    @NotBlank
    private String servico;
}
