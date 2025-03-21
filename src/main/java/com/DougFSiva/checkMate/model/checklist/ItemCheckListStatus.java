package com.DougFSiva.checkMate.model.checklist;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ItemCheckListStatus {

	NAO_VERIFICADO("Não verificado"), 
	OK("Ok"), 
	DESVIADO("Desviado"), 
	EMPRESTADO("Emprestado"), 
	AVARIADO("Avariado");
	
	private final String descricao;

	private ItemCheckListStatus(String descricao) {
		this.descricao = descricao;
	}
	
}
