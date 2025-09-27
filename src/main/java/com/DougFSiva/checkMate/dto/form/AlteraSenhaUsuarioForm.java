package com.DougFSiva.checkMate.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AlteraSenhaUsuarioForm(
		
		@NotBlank(message = "O e-mail não pode estar vazio.")
	    @Email(message = "E-mail inválido.")
		String email,
		
		@NotBlank(message = "A senha atual não pode estar vazia.")
		String senhaAtual,
		
		@NotBlank(message = "A nova senha não pode estar vazia.")
		String novaSenha
		
		) {

}
