package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimentoStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListCompartimentoResponse {

	private Long ID;
	private CheckListAmbienteResponse checkListAmbiente;
	private Compartimento compartimento;
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private String executorPreenchimentoEntrada;
	private String executorPreenchimentoSaida;
	private CheckListCompartimentoStatus status;
	
	public CheckListCompartimentoResponse(CheckListCompartimento checkList) {
		this.ID = checkList.getID();
		this.checkListAmbiente = new CheckListAmbienteResponse(checkList.getCheckListAmbiente());
		this.dataHoraPreenchimentoEntrada = checkList.getDataHoraPreenchimentoEntrada();
		this.dataHoraPreenchimentoSaida = checkList.getDataHoraPreenchimentoSaida();
		this.executorPreenchimentoEntrada = checkList.getExecutorPreenchimentoEntrada();
		this.executorPreenchimentoSaida = checkList.getExecutorPreenchimentoSaida();
		this.status = checkList.getStatus();
	}
}
