package com.brunomurakami.softplan.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="nome")
    private String nome;

    @Column(name="sexo")
    private char sexo;

    @Email(message = "Email não é válido")
    @Column(name="email")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento não pode ser nula")
    @Past(message = "Data inválida")
    @Column(name="dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name="naturalidade")
    private String naturalidade;

    @Column(name="nacionalidade")
    private String nacionalidade;

    @CPF(message="CPF não válido")
    @NotNull(message = "CPF não pode ser nulo")
    @Column(name="cpf")
    private String cpf;

    @Column(name="dt_cadastro")
    private OffsetDateTime dataCadastro;

    @Column(name="dt_atualizacao")
    private OffsetDateTime dataAtualizacao;

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
