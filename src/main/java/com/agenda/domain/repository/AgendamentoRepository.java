package com.agenda.domain.repository;

import com.agenda.api.model.AgendamentoEvento;
import com.agenda.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("from Agendamento a join fetch a.cliente join fetch a.colaborador where a.id = :id")
    Optional<Agendamento> consultar(@Param("id") Long id);

    @Query(value = "select a.id, c.nome as title, a.horario as date from agendamento a " +
            "inner join cliente c on a.cliente_id = c.id " +
            "where a.usuario_colaborador_id = :colaboradorId", nativeQuery = true)
    List<AgendamentoEvento> consultarEventoPorColaborador(@Param("colaboradorId") Long colaboradorId);
}
