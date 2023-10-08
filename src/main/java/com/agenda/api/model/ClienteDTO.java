package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String telefone;

    public ClienteDTO(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public ClienteDTO() {
    }
}
