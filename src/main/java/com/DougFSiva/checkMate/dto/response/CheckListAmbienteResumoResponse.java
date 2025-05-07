package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListAmbienteResumoResponse {

	private Long ID;
	private AmbienteResponse ambiente;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraEncerramento;
	private CheckListAmbienteStatus status;
	
	public CheckListAmbienteResumoResponse(CheckListAmbiente checklist) {
		this.ID = checklist.getID();
		this.ambiente = new AmbienteResponse(checklist.getAmbiente());
		this.dataHoraLiberacao = checklist.getDataHoraLiberacao();
		this.dataHoraEncerramento = checklist.getDataHoraEncerramento();
		this.status = checklist.getStatus();
	}
}
