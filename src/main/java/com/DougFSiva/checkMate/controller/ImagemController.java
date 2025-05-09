package com.DougFSiva.checkMate.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;
import com.DougFSiva.checkMate.service.imagem.BuscaImagemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/imagens")
@RequiredArgsConstructor
@Tag(name = "Imagens", description = "Endpoint para recuperação de imagens")
public class ImagemController {

	private final BuscaImagemService buscaImagemService;

	@GetMapping("/**")
	@Operation(summary = "Buscar imagem", description = "Retorna uma imagem a partir de um caminho especificado na URL.")
	public ResponseEntity<Resource> buscarImagem(HttpServletRequest request) {
		String pathImagem = request.getRequestURI().replace("/imagens/", "");
	    Resource arquivo = buscaImagemService.buscar(pathImagem);
	    try {
	        String contentType = Files.probeContentType(arquivo.getFile().toPath());
	        if (contentType != null && contentType.startsWith("image/")) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_TYPE, contentType)
	                    .body(arquivo);
	        } else {
	            throw new ErroDeOperacaoComImagemException("Tipo de arquivo não suportado");
	        }
	    } catch (IOException e) {
	        throw new ErroDeOperacaoComImagemException("Erro ao tentar acessar a imagem", e);
	    }
	}
}
