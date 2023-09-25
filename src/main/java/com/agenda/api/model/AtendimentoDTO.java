package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AtendimentoDTO {
    private Long id;
    private AgendamentoDTO agendamento;
    private UsuarioDTO colaborador;
    private BigDecimal valorTotal;
    private Set<ServicoValorDTO> servicosValores = new HashSet<>();
}
