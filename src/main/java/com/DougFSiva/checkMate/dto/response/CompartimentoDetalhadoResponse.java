package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Compartimento;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CompartimentoDetalhadoResponse {

	private Long ID;
	private AmbienteResumoResponse ambiente;
	private String codigo;
	private String descricao;
	private String imagem;
	private int contagemItens;
	
	public CompartimentoDetalhadoResponse(Compartimento compartimento, int contagemItens) {
		this.ID = compartimento.getID();
		this.ambiente = new AmbienteResumoResponse(compartimento.getAmbiente());
		this.codigo = compartimento.getCodigo();
		this.descricao = compartimento.getDescricao();
		this.imagem = compartimento.getImagem();
		this.contagemItens = contagemItens;
	}
}
