package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResumoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private String statusEntrada;
	private String statusSaida;
	private String observacaoEntrada;
	private String observacaoSaida;
	
	public ItemCheckListResumoResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.item = new ItemResumoResponse(item.getItem());
		this.statusEntrada = item.getStatusEntrada().getDescricao();
		this.statusSaida = item.getStatusSaida().getDescricao();
		this.observacaoEntrada = item.getObservacaoEntrada();
		this.observacaoSaida = item.getObservacaoSaida();
	}
	
}
