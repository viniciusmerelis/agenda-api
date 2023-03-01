package com.agenda.domain.model.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class AtendimentoServicoId implements Serializable {
    @Column(name = "atendimento_id", nullable = false)
    private Long atendimentoId;

    @Column(name = "servico_prestado_id", nullable = false)
    private Long servicoPrestadoId;
}
