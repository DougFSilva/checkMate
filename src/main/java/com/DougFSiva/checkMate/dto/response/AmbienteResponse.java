package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Ambiente;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AmbienteResponse {

	private Long ID;
	private String nome;
	private String descricao;
	private String localizacao;
	private String imagem;
	
	public AmbienteResponse(Ambiente ambiente) {
		this.ID = ambiente.getID();
		this.nome = ambiente.getNome();
		this.descricao = ambiente.getDescricao();
		this.localizacao = ambiente.getLocalizacao();
		this.imagem = ambiente.getImagem();
	}
}
