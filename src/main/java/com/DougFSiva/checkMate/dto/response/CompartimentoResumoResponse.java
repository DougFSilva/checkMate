package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Compartimento;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompartimentoResumoResponse {

	private Long ID;
	private String codigo;
	private String descricao;
	private String imagem;
	
	public CompartimentoResumoResponse(Compartimento compartimento) {
		this.ID = compartimento.getID();
		this.codigo = compartimento.getCodigo();
		this.descricao = compartimento.getDescricao();
		this.imagem = compartimento.getImagem();
	}
}
