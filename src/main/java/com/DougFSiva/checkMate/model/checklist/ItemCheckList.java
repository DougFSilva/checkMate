package com.DougFSiva.checkMate.model.checklist;

import com.DougFSiva.checkMate.model.Item;

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
	private Integer quantidade;
	private ItemCheckListStatus statusEntrada;
	private ItemCheckListStatus statusSaida;
	private String observacaoEntrada;
	private String observacaoSaida;

	
	public ItemCheckList(CheckList checkList, Item item) {
		this.checkList = checkList;
		this.compartimento = item.getCompartimento().infoParaItemCheckList();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.statusEntrada = ItemCheckListStatus.NAO_VERIFICADO;
		this.statusSaida = ItemCheckListStatus.NAO_VERIFICADO;
	
	}
	
}
