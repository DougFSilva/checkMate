package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.ocorrrencia.TratamentoOcorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TratamentoOcorrenciaResponse {

	private Long ID;
	private UsuarioDetalhadoResponse autor;
	private String descricao;
	
	public TratamentoOcorrenciaResponse(TratamentoOcorrencia tratamento) {
		this.ID = tratamento.getID();
		this.autor = new UsuarioDetalhadoResponse(tratamento.getAutor());
		this.descricao = tratamento.getDescricao();
	}

}
