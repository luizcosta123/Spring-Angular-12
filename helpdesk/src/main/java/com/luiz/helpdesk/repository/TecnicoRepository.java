package com.luiz.helpdesk.repository;

import com.luiz.helpdesk.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    Tecnico findByCpf(String cpf);

}
