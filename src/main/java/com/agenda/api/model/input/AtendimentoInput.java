package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AtendimentoInput {
    @NotNull
    private Long agendamentoId;

    @NotNull
    private Long colaboradorId;

    private Set<ServicoValorInput> servicosValores = new HashSet<>();
}
