package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ItemCheckListResumoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private ItemCheckListStatus statusEntrada;
	private ItemCheckListStatus statusSaida;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraPreenchimentoEntrada;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraPreenchimentoSaida;
	
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
