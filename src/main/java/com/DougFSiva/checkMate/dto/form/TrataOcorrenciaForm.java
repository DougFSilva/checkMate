package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TrataOcorrenciaForm(
		
		@NotNull(message = "O id da ocorrência deve ser informado")
		Long ocorrenciaID,
		
		@NotBlank(message = "A descrição do tratamento não pode ser vazio")
		String descricao
		
		) {

}
