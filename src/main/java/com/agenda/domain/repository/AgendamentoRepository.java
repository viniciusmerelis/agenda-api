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
    @Query("from Agendamento a join fetch a.cliente join fetch a.colaborador " +
            "where a.colaborador.id = :colaboradorId and extract(month from a.horario) = :mes")
    List<Agendamento> listarPorColaboradorEMes(@Param("colaboradorId") Long colaboradorId, @Param("mes") int mes);

    @Query("from Agendamento a join fetch a.cliente join fetch a.colaborador where a.id = :id")
    Optional<Agendamento> buscarPorId(@Param("id") Long id);

    @Query(value = "select a.id, concat(c.nome, ' - ', sp.nome) as title, a.horario as date from agendamento a " +
            "inner join cliente c on a.cliente_id = c.id " +
            "inner join servico_prestado sp on a.servico_prestado_id = sp.id " +
            "where a.usuario_colaborador_id = :colaboradorId", nativeQuery = true)
    List<AgendamentoEvento> listarEventoPorColaborador(@Param("colaboradorId") Long colaboradorId);
}
