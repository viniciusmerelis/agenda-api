package com.agenda.api.mapper;

import com.agenda.api.model.AtendimentoDTO;
import com.agenda.api.model.ServicoValorDTO;
import com.agenda.api.model.input.AtendimentoInput;
import com.agenda.api.model.input.ServicoValorInput;
import com.agenda.domain.model.Atendimento;
import com.agenda.domain.model.AtendimentoServicoValor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {
    AtendimentoDTO toDto(Atendimento entity);

    List<AtendimentoDTO> toDto(List<Atendimento> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "agendamentoId", target = "agendamento.id")
    @Mapping(source = "colaboradorId", target = "colaborador.id")
    Atendimento toEntity(AtendimentoInput input);

    @Mapping(source = "id.servicoPrestadoId", target = "servicoPrestado.id")
    @Mapping(source = "servicoPrestado.nome", target = "servicoPrestado.nome")
    ServicoValorDTO toServicoValorDto(AtendimentoServicoValor entity);

    @Mapping(source = "servicoPrestadoId", target = "id.servicoPrestadoId")
    AtendimentoServicoValor toServicoValorEntity(ServicoValorInput input);
}
