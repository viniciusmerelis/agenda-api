package com.agenda.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servico_prestado")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServicoPrestado {
    @Id
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "seq_servico_prestado", sequenceName = "seq_servico_prestado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servico_prestado")
    private Long id;

    private String nome;

    public ServicoPrestado(String nome) {
        this.nome = nome;
    }

    public ServicoPrestado() {
    }
}
