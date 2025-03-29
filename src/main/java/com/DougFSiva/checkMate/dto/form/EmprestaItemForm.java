package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotNull;

public record EmprestaItemForm(
		
		@NotNull(message = "O ID do item deve ser informado")
		Long itemID,
		
		@NotNull(message = "O ID do solicitante deve ser informado")
		Long solicitanteID
		) {

}
