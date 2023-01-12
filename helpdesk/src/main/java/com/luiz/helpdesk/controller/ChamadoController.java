package com.luiz.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.luiz.helpdesk.dto.ChamadoDto;
import com.luiz.helpdesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/os")
public class ChamadoController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id) {
		ChamadoDto chamadoDto = new ChamadoDto(chamadoService.findById(id));
		
		return ResponseEntity.ok().body(chamadoDto);
	}
	
	@GetMapping()
	public ResponseEntity<List<ChamadoDto>> findAll() {
		List<ChamadoDto> chamadoDtosList = chamadoService.findAll().stream().map(os -> new ChamadoDto(os)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(chamadoDtosList);
	}
	
	@PostMapping()
	public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto chamadoDto) {
		chamadoDto = new ChamadoDto(chamadoService.create(chamadoDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamadoDto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping()
	public ResponseEntity<ChamadoDto> update(@Valid @RequestBody ChamadoDto chamadoDto) {
		chamadoDto = new ChamadoDto(chamadoService.update(chamadoDto));
		
		return ResponseEntity.ok().body(chamadoDto);
	}

}















