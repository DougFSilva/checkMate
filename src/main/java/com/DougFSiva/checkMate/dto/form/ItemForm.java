package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemForm(
		
		@NotNull(message = "O ID do compartimento deve ser informado")
		Long compartimentoID,
		
		@NotBlank(message = "A descrição não pode ser vazia")
		String descricao,
		
		@NotNull(message = "A quantidade não pode ser vazia")
		Integer quantidade,
	
		@NotNull(message = "O campo verificável não pode ser vazio")
		boolean verificavel) {

}
