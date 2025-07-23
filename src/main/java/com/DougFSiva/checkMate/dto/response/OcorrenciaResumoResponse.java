package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OcorrenciaResumoResponse {

	private Long ID;
	@JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
	private LocalDateTime dataHora;
	
	private String emissor;
	private String ambiente;
	private String compartimento;
	private ItemCheckListResumoResponse itemCheckList;
	private UsuarioResumoResponse responsavelEncerramento;
	private List<TratamentoOcorrenciaResponse> tratamento;
	private boolean encerrada;
	
	public OcorrenciaResumoResponse(Ocorrencia ocorrencia) {
		this.ID = ocorrencia.getID();
		this.dataHora = ocorrencia.getDataHora();
		this.emissor = ocorrencia.getEmissor();
		this.itemCheckList = new ItemCheckListResumoResponse(ocorrencia.getItemCheckList());
		Compartimento compartimento = ocorrencia.getItemCheckList().getCheckListCompartimento().getCompartimento();
		this.ambiente = compartimento.getAmbiente().getNome();
		this.compartimento = compartimento.getCodigo() + " - " + compartimento.getNome();
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
