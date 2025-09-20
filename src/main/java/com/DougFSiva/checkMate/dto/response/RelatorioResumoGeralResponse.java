package com.DougFSiva.checkMate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelatorioResumoGeralResponse {

	private Long totalAmbientes;
	private Long totalCompartimentos;
	private Long totalItens;
	private Long totalCheckList;
	private Long totalOcorrencias;
}
