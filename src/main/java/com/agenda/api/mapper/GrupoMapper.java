package com.agenda.api.mapper;

import com.agenda.api.model.GrupoDTO;
import com.agenda.domain.model.Grupo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toDto(List<Grupo> entitys);
}
