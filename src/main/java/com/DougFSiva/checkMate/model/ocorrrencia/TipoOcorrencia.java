package com.DougFSiva.checkMate.model.ocorrrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TipoOcorrencia {

	ITEM_DESVIADO("Item Desviado"), 
	ITEM_EMPRESTADO("Item Emprestado"), 
	ITEM_AVARIADO("Item avariado");

	private final String descricao;

	private TipoOcorrencia(String descricao) {
		this.descricao = descricao;
	}
	
}
