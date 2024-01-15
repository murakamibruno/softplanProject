package com.brunomurakami.softplan.controller;

import com.brunomurakami.softplan.model.Pessoa;
import com.brunomurakami.softplan.services.PessoaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:9000", "http://localhost:8080"})
@RequestMapping("/pessoa")
@Slf4j
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("/")
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@Valid @PathVariable(value = "id") Long id) throws RuntimeException {
        return pessoaService.getPessoaById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> getPessoaByCpf(@Valid @PathVariable(value = "cpf") String cpf) throws RuntimeException {
        return pessoaService.getPessoaByCpf(cpf);
    }

    @PostMapping("/")
    public ResponseEntity<Pessoa> savePessoa (@Valid @RequestBody Pessoa pessoa) throws RuntimeException {
        return pessoaService.savePessoa(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) throws RuntimeException {
        return pessoaService.updatePessoa(pessoa, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> deletePessoa (@PathVariable("id") Long id) {
        return pessoaService.deletePessoa(id);
    }

}
