package com.DougFSiva.checkMate.model.checklist;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CheckListAmbienteStatus {

	ABERTO("Aberto"), 
	LIBERADO("Liberado"), 
	ENCERRADO("Encerrado");
	
	private final String descricao;

	private CheckListAmbienteStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
