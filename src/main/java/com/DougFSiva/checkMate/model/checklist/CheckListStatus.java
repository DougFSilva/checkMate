package com.DougFSiva.checkMate.model.checklist;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CheckListStatus {

	ABERTO("Aberto"), 
	ENTRADA_PREENCHIDO("Preenchido na entrada"), 
	LIBERADO("Liberado"), 
	SAIDA_PREENCHIDO("Preenchido na saída"), 
	ENCERRADO("Encerrado");
	
	private final String descricao;

	private CheckListStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
