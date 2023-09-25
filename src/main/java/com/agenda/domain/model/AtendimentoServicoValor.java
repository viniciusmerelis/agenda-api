package com.agenda.domain.model;

import com.agenda.domain.model.pk.AtendimentoServicoId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AtendimentoServicoValor {
    @EmbeddedId
    private AtendimentoServicoId id;

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
