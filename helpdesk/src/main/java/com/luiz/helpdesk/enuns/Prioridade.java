package com.luiz.helpdesk.enuns;

public enum Prioridade {
	
	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"),
	ALTA(2, "ALTA");
	
	private Integer key;
	private String descricao;

	Prioridade(Integer key, String descricao) {
		this.key = key;
		this.descricao = descricao;
	}

	public Integer getKey() {
		return key;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer key) {
		
		if(key == null) {
			return null;
		}
		
		for(Prioridade prioridade : Prioridade.values()) {
			if(prioridade.getKey().equals(key)) {
				return prioridade;
			}
		}
		
		throw new IllegalArgumentException("Esta prioridade não existe!");
		
	}

}
