package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Item;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemResponse {

	private Long ID;
	private CompartimentoResponse compartimento;
	private String descricao;
	private Integer quantidade;
	private String imagem;
	
	public ItemResponse(Item item) {
		this.ID = item.getID();
		this.compartimento = new CompartimentoResponse(item.getCompartimento());
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.imagem = item.getImagem();
	}
	
}
