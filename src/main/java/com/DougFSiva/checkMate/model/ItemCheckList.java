package com.DougFSiva.checkMate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCheckList {

	private Long ID;
	private CheckList checkList;
	private String compartimento;
	private String descricao;
	private Integer quantidadeTotal;
	private Integer quantidadeEncontrada;
	private ItemCheckListStatus status;
	private boolean avariado;
	private String observacao;
	
	public ItemCheckList(CheckList checkList, Item item) {
		this.checkList = checkList;
		this.compartimento = item.getCompartimento().infoParaItemCheckList();
		this.descricao = item.getDescricao();
		this.quantidadeTotal = item.getQuantidade();
		this.status = ItemCheckListStatus.NAO_VERIFICADO;
	}
	
}
