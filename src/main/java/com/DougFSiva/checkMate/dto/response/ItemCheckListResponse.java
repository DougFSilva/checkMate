package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResponse {

	private Long ID;
	private CheckList checkList;
	private String compartimento;
	private String descricao;
	private Integer quantidade;
	private String statusEntrada;
	private String statusSaida;
	private String observacaoEntrada;
	private String observacaoSaida;
	
	public ItemCheckListResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.checkList = item.getCheckList();
		this.compartimento = item.getCompartimento();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.statusEntrada = item.getStatusEntrada().getDescricao();
		this.statusSaida = item.getStatusSaida().getDescricao();
		this.observacaoEntrada = item.getObservacaoEntrada();
		this.observacaoSaida = item.getObservacaoSaida();
	}
	
}
