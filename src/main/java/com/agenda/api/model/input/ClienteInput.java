package com.agenda.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInput {
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;
}
