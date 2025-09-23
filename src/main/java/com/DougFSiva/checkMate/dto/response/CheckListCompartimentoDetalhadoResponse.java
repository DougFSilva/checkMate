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
	private UsuarioResponse executorPreenchimentoEntrada;
	private UsuarioResponse executorPreenchimentoSaida;
	private CheckListCompartimentoStatus status;
	
	public CheckListCompartimentoDetalhadoResponse(CheckListCompartimento checkList) {
		this.ID = checkList.getID();
		this.compartimento = new CompartimentoResumoResponse(checkList.getCompartimento());
		this.checkListAmbiente = new CheckListAmbienteResumoResponse(checkList.getCheckListAmbiente());
		this.dataHoraPreenchimentoEntrada = checkList.getDataHoraPreenchimentoEntrada();
		this.dataHoraPreenchimentoSaida = checkList.getDataHoraPreenchimentoSaida();
		if (checkList.getExecutorPreenchimentoEntrada() != null) {
			this.executorPreenchimentoEntrada = new UsuarioResponse(checkList.getExecutorPreenchimentoEntrada());
		}
		if (checkList.getExecutorPreenchimentoSaida() != null) {
			this.executorPreenchimentoSaida = new UsuarioResponse(checkList.getExecutorPreenchimentoSaida());
		}
		this.status = checkList.getStatus();
	}
}
