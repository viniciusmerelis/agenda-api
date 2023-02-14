package com.agenda.api.mapper;

import com.agenda.api.model.PermissaoDTO;
import com.agenda.domain.model.Permissao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissaoMapper {
    PermissaoDTO toDto(Permissao entity);
    List<PermissaoDTO> toDto(List<Permissao> entities);
}
