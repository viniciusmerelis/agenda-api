package com.agenda.domain.repository;

import com.agenda.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query(value = "select u from usuario u " +
            "join usuario_grupo ug on u.id = ug.usuario_id " +
            "where ug.grupo_id = :grupoId", nativeQuery = true)
    List<Usuario> listarPorGrupo(@Param("grupoId") Long grupoId);
}
