package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OcorrenciaResponse {

	private Long ID;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	
	private String emissor;
	private ItemCheckListResumoResponse itemCheckList;
	private UsuarioDetalhadoResponse responsavelEncerramento;
	private List<TratamentoOcorrenciaResponse> tratamento;
	private boolean encerrada;
	
	public OcorrenciaResponse(Ocorrencia ocorrencia) {
		this.ID = ocorrencia.getID();
		this.dataHora = ocorrencia.getDataHora();
		this.emissor = ocorrencia.getEmissor();
		this.itemCheckList = new ItemCheckListResumoResponse(ocorrencia.getItemCheckList());
		if (ocorrencia.getResponsavelEncerramento() != null) {
			this.responsavelEncerramento = new UsuarioDetalhadoResponse(ocorrencia.getResponsavelEncerramento());
		}
		this.tratamento = ocorrencia.getTratamentos()
				.stream()
				.map(TratamentoOcorrenciaResponse::new)
				.toList();
		this.encerrada = ocorrencia.isEncerrada();
	}
}
