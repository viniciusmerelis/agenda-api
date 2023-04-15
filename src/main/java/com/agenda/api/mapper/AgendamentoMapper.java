package com.agenda.api.mapper;

import com.agenda.api.model.AgendamentoDTO;
import com.agenda.api.model.input.AgendamentoInput;
import com.agenda.domain.model.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {
    AgendamentoDTO toDto(Agendamento entity);
    List<AgendamentoDTO> toDto(List<Agendamento> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "clienteId", target = "cliente.id")
    @Mapping(source = "colaboradorId", target = "colaborador.id")
    @Mapping(source = "servicoPrestadoId", target = "servicoPrestado.id")
    Agendamento toEntity(AgendamentoInput input);
}
