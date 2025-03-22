package com.DougFSiva.checkMate.dto.form;

import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;

import jakarta.validation.constraints.NotNull;

public record ItemCheckListForm(
		
		@NotNull(message = "O ID deve ser informado")
		Long ID,
		
		@NotNull(message = "O status não pode ser vazio")
		ItemCheckListStatus status,
		
		String observacao) {

}
