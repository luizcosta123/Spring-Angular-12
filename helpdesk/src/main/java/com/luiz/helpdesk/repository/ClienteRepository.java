package com.luiz.helpdesk.repository;

import com.luiz.helpdesk.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Cliente findByCpf(String cpf);

}
