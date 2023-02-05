package com.agenda.api.mapper;

import com.agenda.api.model.UsuarioDTO;
import com.agenda.domain.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toDto(Usuario entity);
    List<UsuarioDTO> toDto(List<Usuario> entitys);
}
