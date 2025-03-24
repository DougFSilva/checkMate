package com.DougFSiva.checkMate.service.imagem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;

@Service
public class DeletaImagemService {

	@Value("${app.dir.imagens}")
	private String diretorioBase;

	public void deletarImagem(String nomeImagem) {
		Path path = Path.of(diretorioBase, nomeImagem);
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new ErroDeOperacaoComImagemException(String.format("Erro ao deletar imagem: %s", e.getMessage()), e);
		}
	}
}
