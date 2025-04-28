package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.NotBlank;

public record AlteraSenhaUsuarioForm(
		
		@NotBlank(message = "A senha atual não pode estar vazia.")
		String senhaAtual,
		
		@NotBlank(message = "A nova senha não pode estar vazia.")
		String novaSenha
		
		) {

}
