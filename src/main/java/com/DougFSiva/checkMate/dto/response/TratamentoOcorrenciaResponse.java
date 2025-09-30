package com.DougFSiva.checkMate.dto.response;

import java.time.OffsetDateTime;

import com.DougFSiva.checkMate.model.ocorrrencia.TratamentoOcorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TratamentoOcorrenciaResponse {

	private Long ID;
	private OffsetDateTime dataHora;
	private UsuarioResponse autor;
	private String descricao;
	
	public TratamentoOcorrenciaResponse(TratamentoOcorrencia tratamento) {
		this.ID = tratamento.getID();
		this.dataHora = tratamento.getDataHora();
		this.autor = new UsuarioResponse(tratamento.getAutor());
		this.descricao = tratamento.getDescricao();
	}

}
