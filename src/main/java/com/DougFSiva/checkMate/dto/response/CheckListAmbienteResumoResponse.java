package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListAmbienteResumoResponse {

	private Long ID;
	private AmbienteResumoResponse ambiente;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraLiberacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraEncerramento;
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
