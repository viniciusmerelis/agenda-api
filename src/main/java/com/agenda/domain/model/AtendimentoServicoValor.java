package com.agenda.domain.model;

import com.agenda.domain.model.pk.AtendimentoServicoId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "atendimento_servico_prestado")
@IdClass(AtendimentoServicoId.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtendimentoServicoValor {
    @Id
    private Long atendimentoId;

    @Id
    private Long servicoPrestadoId;

    @MapsId("atendimentoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Atendimento atendimento;

    @MapsId("servicoPrestadoId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_prestado_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ServicoPrestado servicoPrestado;

    @Column(nullable = false)
    private BigDecimal valor;
}
