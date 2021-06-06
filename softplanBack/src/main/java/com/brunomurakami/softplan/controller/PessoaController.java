package com.brunomurakami.softplan.controller;

import com.brunomurakami.softplan.exception.CpfDuplicado;
import com.brunomurakami.softplan.exception.PessoaNotFound;
import com.brunomurakami.softplan.model.Pessoa;
import com.brunomurakami.softplan.model.User;
import com.brunomurakami.softplan.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/")
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@Valid @PathVariable(value = "id") Long id) throws RuntimeException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> getPessoaByCpf(@Valid @PathVariable(value = "cpf") String cpf) throws RuntimeException {
        Optional<Pessoa> pessoa = pessoaRepository.findPersonByCPF(cpf);
        if (pessoa.isPresent()) {
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        } else {
            throw new PessoaNotFound(cpf);
        }
    }


    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    @PostMapping("/")
    public ResponseEntity<Pessoa> savePessoa (@Valid @RequestBody Pessoa pessoa) throws RuntimeException {
        Optional<Pessoa> pessoaByCpf = pessoaRepository.findPersonByCPF(pessoa.getCpf());
        if (pessoaByCpf.isPresent()) {
            throw new CpfDuplicado(pessoa.getCpf());
        } else {
            pessoa.setDataCadastro(OffsetDateTime.now());
            pessoa.setDataAtualizacao(OffsetDateTime.now());
            Pessoa pessoaCreated = pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoaCreated, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) throws RuntimeException {
        if (pessoaRepository.existsById(id)) {
            Pessoa oldPessoa = pessoaRepository.findById(id).get();
            if (!oldPessoa.getCpf().equals(pessoa.getCpf())) {
                Optional<Pessoa> pessoaByCpf = pessoaRepository.findPersonByCPF(pessoa.getCpf());
                if (pessoaByCpf.isPresent()) {
                    throw new CpfDuplicado(pessoa.getCpf());
                }
            }
            pessoa.setDataCadastro(oldPessoa.getDataCadastro());
            pessoa.setId(id);
            pessoa.setDataAtualizacao(OffsetDateTime.now());
            Pessoa pessoaUpdated = pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoaUpdated, HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> deletePessoa (@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            Pessoa pessoaDeleted = pessoa.get();
            pessoaRepository.delete(pessoaDeleted);
            return new ResponseEntity<Pessoa>(pessoaDeleted, HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }

}
