package com.brunomurakami.softplan.services;

import com.brunomurakami.softplan.exception.CpfDuplicado;
import com.brunomurakami.softplan.exception.PessoaNotFound;
import com.brunomurakami.softplan.model.Pessoa;
import com.brunomurakami.softplan.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    private final RedisCacheService redisCacheService;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public ResponseEntity<Pessoa> getPessoaById(@Valid @PathVariable(value = "id") Long id) throws RuntimeException {
        Optional<Pessoa> pessoa;
        if (redisCacheService.existsForKey(id.toString())) {
            pessoa = Optional.ofNullable(redisCacheService.get(id.toString()));
            log.info("Pessoa obtida através de cache");
        } else {
            pessoa = pessoaRepository.findById(id);
            redisCacheService.save(id.toString(), pessoa.get());
            log.info("Inserido pessoa com chave {}", id);
        }
        if (pessoa.isPresent()) {
            return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }

    public ResponseEntity<Pessoa> getPessoaByCpf(@Valid @PathVariable(value = "cpf") String cpf) throws RuntimeException {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        if (pessoa.isPresent()) {
            return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
        } else {
            throw new PessoaNotFound(cpf);
        }
    }

    public ResponseEntity<Pessoa> savePessoa (@Valid @RequestBody Pessoa pessoa) throws RuntimeException {
        Optional<Pessoa> pessoaByCpf = pessoaRepository.findByCpf(pessoa.getCpf());
        if (pessoaByCpf.isPresent()) {
            String errorMsg = String.format("Pessoa com cpf: %s duplicado", pessoa.getCpf());
            throw new CpfDuplicado(errorMsg);
        } else {
            OffsetDateTime now = OffsetDateTime.now();
            pessoa.setDataCadastro(now);
            pessoa.setDataAtualizacao(now);
            Pessoa pessoaCreated = pessoaRepository.save(pessoa);
            return new ResponseEntity<>(pessoaCreated, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa, @PathVariable("id") Long id) throws RuntimeException {
        if (pessoaRepository.existsById(id)) {
            Pessoa oldPessoa = pessoaRepository.findById(id).get();
            if (!oldPessoa.getCpf().equals(pessoa.getCpf())) {
                Optional<Pessoa> pessoaByCpf = pessoaRepository.findByCpf(pessoa.getCpf());
                if (pessoaByCpf.isPresent()) {
                    String errorMsg = String.format("Pessoa com cpf: %s duplicado", pessoa.getCpf());
                    throw new CpfDuplicado(errorMsg);
                }
            }
            pessoa.setDataCadastro(oldPessoa.getDataCadastro());
            pessoa.setId(id);
            pessoa.setDataAtualizacao(OffsetDateTime.now());
            Pessoa pessoaUpdated = pessoaRepository.save(pessoa);
            if (redisCacheService.existsForKey(id.toString())) {
                redisCacheService.save(id.toString(), pessoaUpdated);
                log.info("Pessoa atualizada no cache");
            }
            return new ResponseEntity<>(pessoaUpdated, HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }

    public ResponseEntity<Pessoa> deletePessoa (@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            Pessoa pessoaDeleted = pessoa.get();
            pessoaRepository.delete(pessoaDeleted);
            if (redisCacheService.existsForKey(id.toString())) {
                redisCacheService.remove(id.toString());
            }
            return new ResponseEntity<>(pessoaDeleted, HttpStatus.OK);
        } else {
            throw new PessoaNotFound(id);
        }
    }
}
