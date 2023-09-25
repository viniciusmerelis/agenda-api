package com.agenda.domain.repository;

import com.agenda.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    @Query("from Atendimento a join fetch a.agendamento join fetch a.colaborador join fetch a.servicosValores")
    List<Atendimento> listar();
}
