package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Item;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemResumoResponse {

	private Long ID;
	private String descricao;
	private Integer quantidade;
	private boolean verificavel;
	private String imagem;
	
	public ItemResumoResponse(Item item) {
		this.ID = item.getID();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.verificavel = item.isVerificavel();
		this.imagem = item.getImagem();
	}
	
}
