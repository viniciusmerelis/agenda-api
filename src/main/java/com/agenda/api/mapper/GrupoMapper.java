package com.agenda.api.mapper;

import com.agenda.api.model.GrupoDTO;
import com.agenda.api.model.input.GrupoInput;
import com.agenda.domain.model.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {
    GrupoDTO toDto(Grupo entity);
    List<GrupoDTO> toDto(List<Grupo> entities);
    @Mapping(target = "id", ignore = true)
    Grupo toEntity(GrupoInput input);
}
