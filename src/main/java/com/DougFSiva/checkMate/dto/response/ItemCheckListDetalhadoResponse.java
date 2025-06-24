package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListDetalhadoResponse {

	private Long ID;
	private CheckListCompartimentoDetalhadoResponse checkListCompartimento;
	private ItemResumoResponse item;
	private ItemCheckListStatus statusEntrada;
	private ItemCheckListStatus statusSaida;
	private String observacaoEntrada;
	private String observacaoSaida;
	
	public ItemCheckListDetalhadoResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.checkListCompartimento = new CheckListCompartimentoDetalhadoResponse(item.getCheckListCompartimento());
		this.item = new ItemResumoResponse(item.getItem());
		this.statusEntrada = item.getStatusEntrada();
		this.statusSaida = item.getStatusSaida();
		this.observacaoEntrada = item.getObservacaoEntrada();
		this.observacaoSaida = item.getObservacaoSaida();
	}
	
}
