package com.luiz.helpdesk.service;

import java.util.List;
import java.util.Optional;

import com.luiz.helpdesk.dto.ClienteDto;
import com.luiz.helpdesk.exception.DataIntegrityViolationExceptionChamado;
import com.luiz.helpdesk.exception.ObjectNotFoundExceptionChamado;
import com.luiz.helpdesk.model.Cliente;
import com.luiz.helpdesk.model.Pessoa;
import com.luiz.helpdesk.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundExceptionChamado("Objeto não encontrado!"
				   												   + " / Id: " + id
				   												   + " / Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDto clienteDto) {
		if(findByCpf(clienteDto) != null) {
			throw new DataIntegrityViolationExceptionChamado("CPF já cadastrado no banco de dados!");
		}
		
		return clienteRepository.save(new Cliente(null, clienteDto.getName(), clienteDto.getCpf(), clienteDto.getPhone()));
	}

	public Cliente update(Integer id, @Valid ClienteDto clienteDto) {
		
		Cliente oldCliente = findById(id);

		if(findByCpf(clienteDto) != null && findByCpf(clienteDto).getId() != id) {
			return clienteRepository.save(new Cliente(null, clienteDto.getName(), clienteDto.getCpf(), clienteDto.getPhone()));
		}
		
		oldCliente.setName(clienteDto.getName());
		oldCliente.setCpf(clienteDto.getCpf());
		oldCliente.setPhone(clienteDto.getPhone());

		return clienteRepository.save(oldCliente);
	}
	
	public void delete(Integer id) {
		Cliente cliente = findById(id);
		
		if(cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationExceptionChamado("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}
		
		clienteRepository.deleteById(id);
	}
	
	public Pessoa findByCpf(ClienteDto clienteDto) {
		Pessoa cliente = clienteRepository.findByCpf(clienteDto.getCpf());

		if(cliente != null) {
			return cliente;
		}

		return null;
	}

}
