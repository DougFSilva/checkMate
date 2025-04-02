package com.DougFSiva.checkMate.model.checklist;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CheckListCompartimentoStatus {

	NAO_PREENCHIDO("Não Preenchido"),
	ENTRADA_PREENCHIDO("Preenchido na entrada"), 
	SAIDA_PREENCHIDO("Preenchido na saída");
	
	private final String descricao;

	private CheckListCompartimentoStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
