package com.agenda.api.mapper;

import com.agenda.api.model.ClienteDTO;
import com.agenda.api.model.input.ClienteInput;
import com.agenda.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDto(Cliente entity);
    List<ClienteDTO> toDto(List<Cliente> entities);
    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteInput input);
}
