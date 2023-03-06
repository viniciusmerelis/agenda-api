package com.agenda.api.mapper;

import com.agenda.api.model.ServicoPrestadoDTO;
import com.agenda.api.model.input.ServicoPrestadoInput;
import com.agenda.domain.model.ServicoPrestado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoPrestadoMapper {
    ServicoPrestadoDTO toDto(ServicoPrestado entity);
    List<ServicoPrestadoDTO> toDto(List<ServicoPrestado> entities);
    @Mapping(target = "id", ignore = true)
    ServicoPrestado toEntity(ServicoPrestadoInput input);
}
