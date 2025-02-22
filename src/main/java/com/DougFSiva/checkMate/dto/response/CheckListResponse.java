package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListResponse {

	private Long ID;
	private AmbienteResponse ambiente;
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private LocalDateTime dataHoraEncerramento;
	private String executorPreenchimentoEntrada;
	private UsuarioResponse responsavelLiberacao;
	private String executorPreenchimentoSaida;
	private UsuarioResponse responsavelEncerramento;
	private CheckListStatus status;
	private String observacoes;
	private boolean desvio;
	
	public CheckListResponse(CheckList checklist) {
		this.ID = checklist.getID();
		this.ambiente = new AmbienteResponse(checklist.getAmbiente());
		this.dataHoraPreenchimentoEntrada = checklist.getDataHoraPreenchimentoEntrada();
		this.dataHoraLiberacao = checklist.getDataHoraLiberacao();
		this.dataHoraPreenchimentoSaida = checklist.getDataHoraPreenchimentoSaida();
		this.dataHoraEncerramento = checklist.getDataHoraEncerramento();
		this.executorPreenchimentoEntrada = checklist.getExecutorPreenchimentoEntrada();
		this.responsavelLiberacao = new UsuarioResponse(checklist.getResponsavelLiberacao());
		this.executorPreenchimentoSaida = checklist.getExecutorPreenchimentoSaida();
		this.responsavelEncerramento = new UsuarioResponse(checklist.getResponsavelEncerramento());
		this.status = checklist.getStatus();
		this.observacoes = checklist.getObservacoes();
		this.desvio = checklist.isDesvio();
	}
}
