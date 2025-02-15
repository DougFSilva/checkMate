package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.ItemCheckList;
import com.DougFSiva.checkMate.model.ItemCheckListStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResponse {

	private Long ID;
	private Long checkListID;
	private String compartimento;
	private String descricao;
	private Integer quantidadeTotal;
	private Integer quantidadeEncontrada;
	private ItemCheckListStatus status;
	private boolean avariado;
	private String observacao;
	
	public ItemCheckListResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.checkListID = item.getCheckList().getID();
		this.compartimento = item.getCompartimento();
		this.descricao = item.getDescricao();
		this.quantidadeTotal = item.getQuantidadeTotal();
		this.quantidadeEncontrada = item.getQuantidadeEncontrada();
		this.status = item.getStatus();
		this.avariado = item.isAvariado();
		this.observacao = item.getObservacao();
	}
	
}
