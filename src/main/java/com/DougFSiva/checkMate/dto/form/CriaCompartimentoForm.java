package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriaCompartimentoForm(
		
		@NotNull(message = "O ID do ambiente deve ser informado.")
		Long ambienteID,
		
		String codigo,
		
		@NotBlank(message = "O nome não pode ser vazio.")
		String nome,
		
		String descricao
		) {
}
