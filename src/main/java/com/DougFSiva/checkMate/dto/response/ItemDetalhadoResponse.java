package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.Item;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemDetalhadoResponse {

	private Long ID;
	private CompartimentoDetalhadoResponse compartimento;
	private String descricao;
	private Integer quantidade;
	private boolean verificavel;
	private String imagem;
	
	public ItemDetalhadoResponse(Item item) {
		this.ID = item.getID();
		this.compartimento = new CompartimentoDetalhadoResponse(item.getCompartimento());
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.verificavel = item.isVerificavel();
		this.imagem = item.getImagem();
	}
	
}
