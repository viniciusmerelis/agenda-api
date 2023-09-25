package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class AgendamentoInput {
    @NotNull
    private OffsetDateTime horario;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long colaboradorId;
}
