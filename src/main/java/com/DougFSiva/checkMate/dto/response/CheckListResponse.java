package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.checklist.CheckList;

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
	private UsuarioResponse responsavelAbertura;
	private String executorPreenchimentoEntrada;
	private UsuarioResponse responsavelLiberacao;
	private String executorPreenchimentoSaida;
	private UsuarioResponse responsavelEncerramento;
	private String status;
	private String observacoes;
	
	public CheckListResponse(CheckList checkList) {
		this.ID = checkList.getID();
		this.ambiente = new AmbienteResponse(checkList.getAmbiente());
		this.dataHoraPreenchimentoEntrada = checkList.getDataHoraPreenchimentoEntrada();
		this.dataHoraLiberacao = checkList.getDataHoraLiberacao();
		this.dataHoraPreenchimentoSaida = checkList.getDataHoraPreenchimentoSaida();
		this.dataHoraEncerramento = checkList.getDataHoraEncerramento();
		this.responsavelAbertura = new UsuarioResponse(checkList.getResponsavelAbertura());
		this.executorPreenchimentoEntrada = checkList.getExecutorPreenchimentoEntrada();
		this.responsavelLiberacao = new UsuarioResponse(checkList.getResponsavelLiberacao());
		this.executorPreenchimentoSaida = checkList.getExecutorPreenchimentoSaida();
		this.responsavelEncerramento = new UsuarioResponse(checkList.getResponsavelEncerramento());
		this.status = checkList.getStatus().getDescricao();
		this.observacoes = checkList.getObservacoes();
	}
}
