package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoValorDTO {
    private ServicoPrestadoDTO servicoPrestado;
    private BigDecimal valor;
}
