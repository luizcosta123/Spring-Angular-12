package com.luiz.helpdesk.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.luiz.helpdesk.dto.ChamadoDto;
import com.luiz.helpdesk.enuns.Prioridade;
import com.luiz.helpdesk.enuns.Status;
import com.luiz.helpdesk.exception.ObjectNotFoundExceptionChamado;
import com.luiz.helpdesk.model.Chamado;
import com.luiz.helpdesk.repository.ChamadoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> ordemServico = chamadoRepository.findById(id);
		
		return ordemServico.orElseThrow(() -> new ObjectNotFoundExceptionChamado("Objeto não encontrado!"
				   														+ " / Id: " + id
				   														+ " / Tipo: " + Chamado.class.getName()));
	}
	
	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDto chamadoDto) {
		return fromDto(chamadoDto);
	}
	
	public Chamado update(@Valid ChamadoDto chamadoDto) {
		findById(chamadoDto.getId());
		return fromDto(chamadoDto);
	}
	
	public Chamado fromDto(ChamadoDto chamadoDto) {
		Chamado chamado = new Chamado();
		
		chamado.setId(chamadoDto.getId());
		chamado.setObservacoes(chamadoDto.getObservacoes());
		chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
		chamado.setTecnico(tecnicoService.findById(chamadoDto.getTecnico()));
		chamado.setCliente(clienteService.findById(chamadoDto.getCliente()));
		
		if(chamado.getStatus().getKey().equals(2)) {
			chamado.setDataFechamento(LocalDateTime.now());
		}
		
		return chamadoRepository.save(chamado);
	}

}
