package com.agenda.common;

import com.agenda.api.model.ClienteDTO;
import com.agenda.api.model.input.ClienteInput;
import com.agenda.domain.model.Cliente;

import java.io.File;

public class ClienteConstants {
    public static final Cliente CLIENTE = new Cliente(1L, "Cliente", "27999999999");
    public static final ClienteDTO CLIENTE_DTO = new ClienteDTO(1L, "Cliente", "27999999999");
    public static final ClienteInput CLIENTE_INPUT = new ClienteInput("Cliente", "27999999999");

    private static final String FILE_BASE_PATH = "src/test/resources/payloads";
    public static final File CLIENTE_JSON = new File(FILE_BASE_PATH + "/cliente-input.json");
}
