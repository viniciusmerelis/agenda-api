package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorMesInputDTO {
    @NotNull
    private Long colaboradorId;

    @NotNull
    private Long mes;
}
