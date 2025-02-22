package com.DougFSiva.checkMate.dto.form;

import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;

import jakarta.validation.constraints.NotNull;

public record ItemCheckListForm(
		
		@NotNull(message = "O ID deve ser informado")
		Long ID,
		
		@NotNull(message = "A quantidade encontrada deve ser informada")
		Integer quantidadeEncontrada,
		
		@NotNull(message = "O status não pode ser vazio")
		ItemCheckListStatus status,
		
		@NotNull(message = "O status avariado não pode ser vazio")
		boolean avariado,
		
		String observacao) {

}
