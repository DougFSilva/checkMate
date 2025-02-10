package com.DougFSiva.checkMate.dto.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public record CriaAmbienteForm(
		
		
		@NotBlank(message = "O nome não pode estar vazio.")
		String nome,
		
		String descricao,
		
		@NotBlank(message = "A localização não pode estar vazia.")
		String localizacao,
		
		MultipartFile imagem) {

}
