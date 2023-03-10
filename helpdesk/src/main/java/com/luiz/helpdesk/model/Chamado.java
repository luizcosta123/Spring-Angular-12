package com.luiz.helpdesk.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luiz.helpdesk.enuns.Prioridade;
import com.luiz.helpdesk.enuns.Status;
import jakarta.persistence.*;

@Entity
public class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:MM")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:MM")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	private String observacoes;
	private Integer status;
	
	@ManyToOne // São n (muitas) Ordens de serviço para 1 técnico
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;
	
	@ManyToOne // São n (muitas) Ordens de serviço para 1 cliente
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public Chamado() {
		super();
		this.setDataAbertura(LocalDateTime.now());
		this.setPrioridade(Prioridade.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public Chamado(Integer id, Prioridade prioridade, String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());
		this.dataFechamento = dataFechamento;
		this.prioridade = (prioridade == null) ? 0 : prioridade.getKey();
		this.observacoes = observacoes;
		this.status = (status == null) ? 0 : status.getKey();
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getKey();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getKey();
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataAbertura, dataFechamento, id, observacoes, prioridade, status, tecnico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataAbertura, other.dataAbertura)
				&& Objects.equals(dataFechamento, other.dataFechamento) && Objects.equals(id, other.id)
				&& Objects.equals(observacoes, other.observacoes) && prioridade == other.prioridade
				&& Objects.equals(status, other.status) && Objects.equals(tecnico, other.tecnico);
	}
	
}
