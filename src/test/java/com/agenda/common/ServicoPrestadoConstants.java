package com.agenda.common;

import com.agenda.api.model.ServicoPrestadoDTO;
import com.agenda.api.model.input.ServicoPrestadoInput;
import com.agenda.domain.model.ServicoPrestado;

import java.io.File;

public class ServicoPrestadoConstants {
    public static final ServicoPrestado SERVICO_PRESTADO = new ServicoPrestado("Serviço Prestado");
    public static final ServicoPrestadoDTO SERVICO_PRESTADO_DTO = new ServicoPrestadoDTO(1L, "Serviço Prestado");
    public static final ServicoPrestadoInput SERVICO_PRESTADO_INPUT = new ServicoPrestadoInput("Serviço Prestado");

    private static final String FILE_BASE_PATH = "src/test/resources/payloads";
    public static final File SERVICO_PRESTADO_JSON = new File(FILE_BASE_PATH + "/servico-prestado-input.json");
}
