package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OcorrenciaResponse {

	private Long ID;
	private LocalDateTime dataHora;
	private String emissor;
	private ItemCheckListResponse itemCheckList;
	private UsuarioResponse responsavelEncerramento;
	private List<TratamentoOcorrenciaResponse> tratamento;
	private boolean encerrada;
	
	public OcorrenciaResponse(Ocorrencia ocorrencia) {
		this.ID = ocorrencia.getID();
		this.dataHora = ocorrencia.getDataHora();
		this.emissor = ocorrencia.getEmissor();
		this.itemCheckList = new ItemCheckListResponse(ocorrencia.getItemCheckList());
		this.responsavelEncerramento = new UsuarioResponse(ocorrencia.getResponsavelEncerramento());
		this.tratamento = ocorrencia.getTratamentos()
				.stream()
				.map(TratamentoOcorrenciaResponse::new)
				.toList();
		this.encerrada = ocorrencia.isEncerrada();
	}
}
