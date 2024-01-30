package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoValorInput {
    @NotNull
    private Long servicoPrestadoId;
    @NotNull
    @PositiveOrZero
    private BigDecimal valor;
}
