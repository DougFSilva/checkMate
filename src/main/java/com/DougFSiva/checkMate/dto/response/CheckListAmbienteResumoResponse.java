package com.DougFSiva.checkMate.dto.response;

import java.time.OffsetDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListAmbienteResumoResponse {

	private Long ID;
	private AmbienteResumoResponse ambiente;
	private OffsetDateTime dataHoraAbertura;
	private OffsetDateTime dataHoraLiberacao;
	private OffsetDateTime dataHoraEncerramento;
	private CheckListAmbienteStatus status;
	
	public CheckListAmbienteResumoResponse(CheckListAmbiente checklist) {
		this.ID = checklist.getID();
		this.ambiente = new AmbienteResumoResponse(checklist.getAmbiente());
		this.dataHoraAbertura = checklist.getDataHoraAbertura();
		this.dataHoraLiberacao = checklist.getDataHoraLiberacao();
		this.dataHoraEncerramento = checklist.getDataHoraEncerramento();
		this.status = checklist.getStatus();
	}
}
