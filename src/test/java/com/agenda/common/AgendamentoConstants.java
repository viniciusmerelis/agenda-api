package com.agenda.common;

import com.agenda.api.model.AgendamentoDTO;
import com.agenda.api.model.input.AgendamentoInput;
import com.agenda.domain.model.Agendamento;

import java.io.File;
import java.time.OffsetDateTime;

public class AgendamentoConstants {
    public static final Agendamento AGENDAMENTO = new Agendamento(OffsetDateTime.now(), ClienteConstants.CLIENTE, UsuarioConstants.USUARIO);
    public static final AgendamentoDTO AGENDAMENTO_DTO = new AgendamentoDTO(1L, OffsetDateTime.now(), ClienteConstants.CLIENTE_DTO, UsuarioConstants.USUARIO_DTO);
    public static final AgendamentoInput AGENDAMENTO_INPUT = new AgendamentoInput(OffsetDateTime.now(), 1L, 1L);
    public static final AgendamentoInput AGENDAMENTO_INPUT_INVALID = new AgendamentoInput(null, null, null);
    private static final String FILE_BASE_PATH = "src/test/resources/payloads";
    public static final File AGENDAMENTO_JSON = new File(FILE_BASE_PATH + "/agendamento-input.json");
    public static final String BASE_URI = "/api/agendamentos";
}
