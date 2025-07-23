package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.ocorrrencia.TratamentoOcorrencia;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TratamentoOcorrenciaResponse {

	private Long ID;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataHora;
	private UsuarioResumoResponse autor;
	private String descricao;
	
	public TratamentoOcorrenciaResponse(TratamentoOcorrencia tratamento) {
		this.ID = tratamento.getID();
		this.dataHora = tratamento.getDataHora();
		this.autor = new UsuarioResumoResponse(tratamento.getAutor());
		this.descricao = tratamento.getDescricao();
	}

}
