package com.agenda.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoValorInput {
    private Long servicoPrestadoId;
    private BigDecimal valor;
}
