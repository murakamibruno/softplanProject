package com.brunomurakami.softplan.exception;

public class PessoaNotFound extends RuntimeException {
    public PessoaNotFound(Long id) {
        super(String.format("Pessoa com id: " + id + " não encontrada"));
    }

    public PessoaNotFound(String cpf) {
        super(String.format("Pessoa com cpf: " + cpf + " não encontrada"));
    }
}
