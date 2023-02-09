package com.agenda.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PermissaoIdInputDTO {
    @NotNull
    private List<Long> permissoesIds = new ArrayList<>();
}
