package com.agenda.domain.exception;

public class AtendimentoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public AtendimentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public AtendimentoNaoEncontradoException(Long id) {
        this(String.format("Não existe um atendimento com código %d", id));
    }
}
