package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AgendamentoInput {
    @NotNull
    private OffsetDateTime horario;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long colaboradorId;

    @NotNull
    private Long servicoPrestadoId;
}
