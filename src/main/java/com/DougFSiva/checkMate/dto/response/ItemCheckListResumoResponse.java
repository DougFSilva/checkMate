package com.DougFSiva.checkMate.dto.response;

import java.time.OffsetDateTime;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResumoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private ItemCheckListStatus statusEntrada;
	private ItemCheckListStatus statusSaida;
	private OffsetDateTime dataHoraPreenchimentoEntrada;
	private OffsetDateTime dataHoraPreenchimentoSaida;
	private String observacaoEntrada;
	private String observacaoSaida;
	
	public ItemCheckListResumoResponse(ItemCheckList item) {
		this.ID = item.getID();
		this.item = new ItemResumoResponse(item.getItem());
		this.statusEntrada = item.getStatusEntrada();
		this.statusSaida = item.getStatusSaida();
		this.dataHoraPreenchimentoEntrada = item.getCheckListCompartimento().getDataHoraPreenchimentoEntrada();
		this.dataHoraPreenchimentoSaida = item.getCheckListCompartimento().getDataHoraPreenchimentoSaida();
		this.observacaoEntrada = item.getObservacaoEntrada();
		this.observacaoSaida = item.getObservacaoSaida();
	}
	
}
