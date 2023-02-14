package com.agenda.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInputDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;
}
