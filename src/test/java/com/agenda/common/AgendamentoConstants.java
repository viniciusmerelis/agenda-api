package com.agenda.common;

import com.agenda.domain.model.Agendamento;

import java.time.OffsetDateTime;

public class AgendamentoConstants {
    public static final Agendamento AGENDAMENTO = new Agendamento(OffsetDateTime.now(), ClienteConstants.CLIENTE, UsuarioConstants.USUARIO);
}
