package com.luiz.helpdesk.service;

import java.util.List;
import java.util.Optional;

import com.luiz.helpdesk.dto.TecnicoDto;
import com.luiz.helpdesk.exception.DataIntegrityViolationExceptionChamado;
import com.luiz.helpdesk.exception.ObjectNotFoundExceptionChamado;
import com.luiz.helpdesk.model.Pessoa;
import com.luiz.helpdesk.model.Tecnico;
import com.luiz.helpdesk.repository.PessoaRepository;
import com.luiz.helpdesk.repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		
		return tecnico.orElseThrow(() -> new ObjectNotFoundExceptionChamado("Objeto não encontrado!"
																   + " / Id: " + id
																   + " / Tipo: " + Tecnico.class.getName()));
		
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDto tecnicoDto) {
		if(findByCpf(tecnicoDto) != null) {
			throw new DataIntegrityViolationExceptionChamado("CPF já cadastrado no banco de dados!");
		}

		return tecnicoRepository.save(new Tecnico(null, tecnicoDto.getName(), tecnicoDto.getCpf(), tecnicoDto.getPhone()));
	}

    public Tecnico update(Integer id, @Valid TecnicoDto tecnicoDto) {
		Tecnico oldTecnico = findById(id);

		if(findByCpf(tecnicoDto) != null && findByCpf(tecnicoDto).getId() != id) {
			return tecnicoRepository.save(new Tecnico(null, tecnicoDto.getName(), tecnicoDto.getCpf(), tecnicoDto.getPhone()));
		}

		oldTecnico.setName(tecnicoDto.getName());
		oldTecnico.setCpf(tecnicoDto.getCpf());
		oldTecnico.setPhone(tecnicoDto.getPhone());

		return tecnicoRepository.save(oldTecnico);
    }

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);

		if(tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationExceptionChamado("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}

		tecnicoRepository.deleteById(id);
	}

	public Pessoa findByCpf(TecnicoDto tecnicoDto) {
		Pessoa tecnico = pessoaRepository.findByCpf(tecnicoDto.getCpf());

		if(tecnico != null) {
			return tecnico;
		}

		return null;
	}

}
