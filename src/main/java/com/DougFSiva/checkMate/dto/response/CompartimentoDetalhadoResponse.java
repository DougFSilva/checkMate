package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Compartimento;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompartimentoDetalhadoResponse {

	private Long ID;
	private AmbienteResponse ambiente;
	private String codigo;
	private String descricao;
	private String imagem;
	
	public CompartimentoDetalhadoResponse(Compartimento compartimento) {
		this.ID = compartimento.getID();
		this.ambiente = new AmbienteResponse(compartimento.getAmbiente());
		this.codigo = compartimento.getCodigo();
		this.descricao = compartimento.getDescricao();
		this.imagem = compartimento.getImagem();
	}
}
