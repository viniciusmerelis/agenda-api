package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoInput {
    @NotNull
    private OffsetDateTime horario;
    @NotNull
    private Long clienteId;
    @NotNull
    private Long colaboradorId;
}
