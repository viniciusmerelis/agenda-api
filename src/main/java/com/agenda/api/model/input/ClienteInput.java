package com.agenda.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {
    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;
}
