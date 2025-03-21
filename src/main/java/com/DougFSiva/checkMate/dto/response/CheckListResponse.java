package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckList;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CheckListResponse {

	private Long ID;
	private String ambiente;
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private LocalDateTime dataHoraEncerramento;
	private UsuarioResponse responsavelAbertura;
	private String executorPreenchimentoEntrada;
	private UsuarioResponse responsavelLiberacao;
	private String executorPreenchimentoSaida;
	private UsuarioResponse responsavelEncerramento;
	private String status;
	private String observacoes;
	
	public CheckListResponse(CheckList checklist) {
		this.ID = checklist.getID();
		this.ambiente = checklist.getAmbiente();
		this.dataHoraPreenchimentoEntrada = checklist.getDataHoraPreenchimentoEntrada();
		this.dataHoraLiberacao = checklist.getDataHoraLiberacao();
		this.dataHoraPreenchimentoSaida = checklist.getDataHoraPreenchimentoSaida();
		this.dataHoraEncerramento = checklist.getDataHoraEncerramento();
		this.responsavelAbertura = new UsuarioResponse(checklist.getResponsavelAbertura());
		this.executorPreenchimentoEntrada = checklist.getExecutorPreenchimentoEntrada();
		this.responsavelLiberacao = new UsuarioResponse(checklist.getResponsavelLiberacao());
		this.executorPreenchimentoSaida = checklist.getExecutorPreenchimentoSaida();
		this.responsavelEncerramento = new UsuarioResponse(checklist.getResponsavelEncerramento());
		this.status = checklist.getStatus().getDescricao();
		this.observacoes = checklist.getObservacoes();
	}
}
