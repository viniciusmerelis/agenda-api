package com.agenda.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoPrestadoInput {
    @NotBlank
    private String nome;
}
