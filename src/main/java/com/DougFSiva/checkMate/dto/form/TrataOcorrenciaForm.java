package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;

public record TrataOcorrenciaForm(
		
		@NotBlank(message = "A descrição do tratamento não pode ser vazio")
		String descricao
		
		) {

}
