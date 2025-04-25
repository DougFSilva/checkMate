package com.DougFSiva.checkMate.dto.form;

import java.time.LocalDate;

import com.DougFSiva.checkMate.model.usuario.TipoPerfil;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioForm(
		
		@NotBlank(message = "O nome não pode estar vazio.")
	    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
		String nome,
		
		@NotBlank(message = "O CPF não pode estar vazio.")
	    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos.")
		String CPF,
		
		@NotBlank(message = "O e-mail não pode estar vazio.")
	    @Email(message = "E-mail inválido.")
		String email,
		
		@NotNull(message = "O tipo de perfil deve ser informado.")
		TipoPerfil tipoPerfil,
		
		@NotNull(message = "A data de validade deve ser informada")
		LocalDate dataValidade) {

}
