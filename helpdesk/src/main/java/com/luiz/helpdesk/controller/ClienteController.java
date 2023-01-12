package com.luiz.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.luiz.helpdesk.dto.ClienteDto;
import com.luiz.helpdesk.model.Cliente;
import com.luiz.helpdesk.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController()
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
		ClienteDto clienteDto = new ClienteDto(clienteService.findById(id));
		
		return ResponseEntity.ok().body(clienteDto);
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> clienteDtoList = clienteService.findAll().stream().map(cliente -> new ClienteDto(cliente)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(clienteDtoList);
	}
	
	@PostMapping()
	public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto clienteDto) {
		Cliente cliente = clienteService.create(clienteDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto clienteDto) {
		ClienteDto newClienteDto = new ClienteDto(clienteService.update(id, clienteDto));
		
		return ResponseEntity.ok().body(newClienteDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}



















