package com.DougFSiva.checkMate.dto.form;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record PreencheCheckListForm(

		@NotNull(message = "O ID deve ser informado.") 
		Long ID,
		
		List<ItemCheckListForm> itens,

		String observacao

) {

}
