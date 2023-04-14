package com.agenda.api.model;

import java.time.LocalDateTime;

public interface AgendamentoEvento {
    Long getId();
    String getTitle();
    LocalDateTime getDate();
}
