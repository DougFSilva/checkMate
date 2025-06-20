package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimentoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListCompartimentoDetalhadoResponse {

	private Long ID;
	private CheckListAmbienteResumoResponse checkListAmbiente;
	private CompartimentoResumoResponse compartimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraPreenchimentoEntrada;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraPreenchimentoSaida;
	private String executorPreenchimentoEntrada;
	private String executorPreenchimentoSaida;
	private CheckListCompartimentoStatus status;
	
	public CheckListCompartimentoDetalhadoResponse(CheckListCompartimento checkList) {
		this.ID = checkList.getID();
		this.compartimento = new CompartimentoResumoResponse(checkList.getCompartimento());
		this.checkListAmbiente = new CheckListAmbienteResumoResponse(checkList.getCheckListAmbiente());
		this.dataHoraPreenchimentoEntrada = checkList.getDataHoraPreenchimentoEntrada();
		this.dataHoraPreenchimentoSaida = checkList.getDataHoraPreenchimentoSaida();
		this.executorPreenchimentoEntrada = checkList.getExecutorPreenchimentoEntrada();
		this.executorPreenchimentoSaida = checkList.getExecutorPreenchimentoSaida();
		this.status = checkList.getStatus();
	}
}
