package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResponse {

	private Long ID;
	private CheckListCompartimento checkListCompartimento;
	private CompartimentoResponse compartimento;
	private String descricao;
	private Integer quantidade;
	private String statusEntrada;
	private String statusSaida;
	private String observacaoEntrada;
	private String observacaoSaida;
	
	public ItemCheckListResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.checkListCompartimento = item.getCheckListCompartimento();
		this.compartimento = new CompartimentoResponse(item.getCompartimento());
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.statusEntrada = item.getStatusEntrada().getDescricao();
		this.statusSaida = item.getStatusSaida().getDescricao();
		this.observacaoEntrada = item.getObservacaoEntrada();
		this.observacaoSaida = item.getObservacaoSaida();
	}
	
}
