package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListAmbienteDetalhadoResponse {

	private Long ID;
	private AmbienteResponse ambiente;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraEncerramento;
	private UsuarioResponse responsavelAbertura;
	private UsuarioResponse responsavelLiberacao;
	private UsuarioResponse responsavelEncerramento;
	private CheckListAmbienteStatus status;
	
	public CheckListAmbienteDetalhadoResponse(CheckListAmbiente checklist) {
		this.ID = checklist.getID();
		this.ambiente = new AmbienteResponse(checklist.getAmbiente());
		this.dataHoraLiberacao = checklist.getDataHoraLiberacao();
		this.dataHoraEncerramento = checklist.getDataHoraEncerramento();
		this.responsavelAbertura = new UsuarioResponse(checklist.getResponsavelAbertura());
		if (checklist.getResponsavelLiberacao() != null) {
			this.responsavelLiberacao = new UsuarioResponse(checklist.getResponsavelLiberacao());
		}
		if (checklist.getResponsavelEncerramento() != null) {
			this.responsavelEncerramento = new UsuarioResponse(checklist.getResponsavelEncerramento());
		}
		this.status = checklist.getStatus();
	}
}
