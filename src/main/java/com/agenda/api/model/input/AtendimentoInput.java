package com.agenda.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AtendimentoInput {
    private Long agendamentoId;
    @NotNull
    private Long colaboradorId;
    @Valid
    @NotNull
    private Set<ServicoValorInput> servicosValores = new HashSet<>();
}
