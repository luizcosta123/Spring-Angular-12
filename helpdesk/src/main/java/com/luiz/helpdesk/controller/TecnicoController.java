package com.luiz.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.luiz.helpdesk.dto.TecnicoDto;
import com.luiz.helpdesk.model.Tecnico;
import com.luiz.helpdesk.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		TecnicoDto tecnicoDto = new TecnicoDto(tecnico);
		
		return ResponseEntity.ok().body(tecnicoDto);
	}

	/*@GetMapping("/cpf")
	public ResponseEntity<TecnicoDto> findByCpf(@RequestBody TecnicoDto tecnicoDto) {
		Tecnico tecnico = tecnicoService.findByCpf(tecnicoDto);
		TecnicoDto newTecnicoDto = new TecnicoDto(tecnico);

		return ResponseEntity.ok().body(newTecnicoDto);
	}*/
	
	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll() {
		List<TecnicoDto> listTecnicoDto = tecnicoService.findAll().stream()
										  .map(tecnico -> new TecnicoDto(tecnico)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listTecnicoDto);
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto tecnicoDto) {
		Tecnico tecnico = tecnicoService.create(tecnicoDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<TecnicoDto> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDto tecnicoDto) {
		TecnicoDto newTecnicoDto = new TecnicoDto(tecnicoService.update(id, tecnicoDto));

		return ResponseEntity.ok().body(newTecnicoDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
