package com.luiz.helpdesk.service;

import java.util.Arrays;

import com.luiz.helpdesk.enuns.Prioridade;
import com.luiz.helpdesk.enuns.Status;
import com.luiz.helpdesk.model.Chamado;
import com.luiz.helpdesk.model.Cliente;
import com.luiz.helpdesk.model.Tecnico;
import com.luiz.helpdesk.repository.ChamadoRepository;
import com.luiz.helpdesk.repository.ClienteRepository;
import com.luiz.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository ordemServicoRepository;
	
	public void instanciaDb() {
		Tecnico t1 = new Tecnico(null, "t1", "590.359.210-46", "(15) 92341-1234");
		Tecnico t2 = new Tecnico(null, "t2", "358.198.860-70", "(15) 91232-3333");
		Tecnico t3 = new Tecnico(null, "t3", "952.763.850-09", "(15) 91232-2222");
		Cliente c1 = new Cliente(null, "c1", "997.511.350-87", "(15) 93122-9998");
		Cliente c2 = new Cliente(null, "c2", "778.161.610-34", "(15) 95123-4212");
		Cliente c3 = new Cliente(null, "c3", "082.954.560-32", "(15) 95221-2412");
		Cliente c4 = new Cliente(null, "c4", "725.096.990-08", "(15) 91234-3412");
		Cliente c5 = new Cliente(null, "c5", "751.432.420-88", "(15) 92326-4521");
		Cliente c6 = new Cliente(null, "c6", "108.025.790-00", "(15) 92322-5212");
		Cliente c7 = new Cliente(null, "c7", "408.023.480-50", "(15) 93222-7221");
		
		Chamado chamado1 = new Chamado(null, Prioridade.ALTA, "Teste - criando OS - 1", Status.ANDAMENTO, t1, c1);
		Chamado chamado2 = new Chamado(null, Prioridade.ALTA, "Teste - criando OS - 2", Status.ABERTO, t2, c2);
		Chamado chamado3 = new Chamado(null, Prioridade.ALTA, "Teste - criando OS - 3", Status.ENCERRADO, t3, c3);
		
		t1.getChamados().add(chamado1);
		c1.getChamados().add(chamado1);
		
		t2.getChamados().add(chamado2);
		c2.getChamados().add(chamado2);
		
		t3.getChamados().add(chamado3);
		t3.getChamados().add(chamado3);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		ordemServicoRepository.saveAll(Arrays.asList(chamado1, chamado2, chamado3));
	}

}
