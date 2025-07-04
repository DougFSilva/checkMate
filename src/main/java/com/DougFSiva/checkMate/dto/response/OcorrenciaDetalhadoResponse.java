package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OcorrenciaDetalhadoResponse {

	private Long ID;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	private String emissor;
	private ItemCheckListDetalhadoResponse itemCheckList;
	private UsuarioResumoResponse responsavelEncerramento;
	private List<TratamentoOcorrenciaResponse> tratamento;
	private boolean encerrada;
	
	public OcorrenciaDetalhadoResponse(Ocorrencia ocorrencia) {
		this.ID = ocorrencia.getID();
		this.dataHora = ocorrencia.getDataHora();
		this.emissor = ocorrencia.getEmissor();
		this.itemCheckList = new ItemCheckListDetalhadoResponse(ocorrencia.getItemCheckList());
		if (ocorrencia.getResponsavelEncerramento() != null) {
			this.responsavelEncerramento = new UsuarioResumoResponse(ocorrencia.getResponsavelEncerramento());
		}
		this.tratamento = ocorrencia.getTratamentos()
				.stream()
				.map(TratamentoOcorrenciaResponse::new)
				.toList();
		this.encerrada = ocorrencia.isEncerrada();
	}
}
