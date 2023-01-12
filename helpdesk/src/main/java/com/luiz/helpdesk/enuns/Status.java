package com.luiz.helpdesk.enuns;

public enum Status {
	
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2, "ENCERRADO");
	
	private Integer key;
	private String descricao;
	
	private Status(Integer key, String descricao) {
		this.key = key;
		this.descricao = descricao;
	}

	public Integer getKey() {
		return key;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer key) {
		
		if(key == null) {
			return null;
		}
		
		for(Status status : Status.values()) {
			if(status.getKey().equals(key)) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Este status não existe!");
		
	}

}
