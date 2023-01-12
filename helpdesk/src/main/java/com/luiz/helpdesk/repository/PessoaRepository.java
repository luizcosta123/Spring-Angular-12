package com.luiz.helpdesk.repository;

import com.luiz.helpdesk.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	Pessoa findByCpf(String cpf);

}
