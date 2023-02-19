package com.agenda.domain.exception;

public class AgendamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public AgendamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AgendamentoNaoEncontradoException(Long id) {
        this(String.format("Não existe um agendamento com código %d", id));
    }
}
