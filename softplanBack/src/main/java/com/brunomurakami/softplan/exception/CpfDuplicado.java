package com.brunomurakami.softplan.exception;

public class CpfDuplicado extends RuntimeException{
    public CpfDuplicado(String cpf) {
        super(String.format("Pessoa com cpf: " + cpf + " duplicado"));
    }
}
