package com.agenda.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoPrestadoDTO {
    private Long id;
    private String nome;

    public ServicoPrestadoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ServicoPrestadoDTO() {
    }
}
