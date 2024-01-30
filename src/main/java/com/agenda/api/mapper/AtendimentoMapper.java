package com.agenda.api.mapper;

import com.agenda.api.model.AtendimentoDTO;
import com.agenda.api.model.ServicoValorDTO;
import com.agenda.api.model.input.AtendimentoInput;
import com.agenda.api.model.input.ServicoValorInput;
import com.agenda.domain.model.Agendamento;
import com.agenda.domain.model.Atendimento;
import com.agenda.domain.model.AtendimentoServicoValor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {
    AtendimentoDTO toDto(Atendimento entity);

    List<AtendimentoDTO> toDto(List<Atendimento> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "agendamentoId", target = "agendamento")
    @Mapping(source = "colaboradorId", target = "colaborador.id")
    Atendimento toEntity(AtendimentoInput input);

    @Mapping(source = "id.servicoPrestadoId", target = "servicoPrestado.id")
    @Mapping(source = "servicoPrestado.nome", target = "servicoPrestado.nome")
    ServicoValorDTO toServicoValorDto(AtendimentoServicoValor entity);

    @Mapping(source = "servicoPrestadoId", target = "id.servicoPrestadoId")
    AtendimentoServicoValor toServicoValorEntity(ServicoValorInput input);

    default Agendamento fromAgendamentoId(Long id) {
        if (isNull(id)) return null;
        Agendamento agendamento = new Agendamento();
        agendamento.setId(id);
        return agendamento;
    }
}
