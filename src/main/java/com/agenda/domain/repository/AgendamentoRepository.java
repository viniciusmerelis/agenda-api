package com.agenda.domain.repository;

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
}
