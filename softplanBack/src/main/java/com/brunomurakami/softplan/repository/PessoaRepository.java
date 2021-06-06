package com.brunomurakami.softplan.repository;

import com.brunomurakami.softplan.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Query(value = "SELECT p.* FROM pessoa p where p.cpf = ?1", nativeQuery = true)
    Optional<Pessoa> findPersonByCPF(String cpf);
}
