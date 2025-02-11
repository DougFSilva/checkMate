package com.DougFSiva.checkMate.dto.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditaCompartimentoForm(
		
		@NotNull(message = "O ID deve ser informado")
		Long ID,
		
		@NotNull(message = "O ID do ambiente deve ser informado.")
		Long ambienteID,
		
		String codigo,
		
		@NotBlank(message = "O nome não pode ser vazio.")
		String nome,
		
		String descricao,
		
		MultipartFile imagem) {
}
