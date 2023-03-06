package com.agenda.domain.exception;

public class ServicoPrestadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ServicoPrestadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ServicoPrestadoNaoEncontradoException(Long id) {
        this(String.format("Não existe um serviço com código %d", id));
    }
}
