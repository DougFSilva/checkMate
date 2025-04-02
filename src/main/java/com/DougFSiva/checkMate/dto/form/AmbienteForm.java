package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;

public record AmbienteForm(
		
		@NotBlank(message = "O nome não pode estar vazio.")
		String nome,
		
		String descricao,
		
		@NotBlank(message = "A localização não pode estar vazia.")
		String localizacao
		
		) {

}
