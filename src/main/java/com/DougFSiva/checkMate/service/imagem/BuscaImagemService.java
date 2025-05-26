package com.DougFSiva.checkMate.service.imagem;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;

@Service
public class BuscaImagemService {

	@Value("${app.dir.imagens}")
	private String diretorioBase;

	public Resource buscar(String nomeImagem) {
		if (nomeImagem.contains("..")) {
			throw new ErroDeOperacaoComImagemException("Nome de imagem inválido");
		}
		Path path = Path.of(diretorioBase, nomeImagem);
		try {
			if (!path.toFile().exists()) {
				throw new ErroDeOperacaoComImagemException("Imagem não encontrada: " + nomeImagem);
			}
			return new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			throw new ErroDeOperacaoComImagemException("Erro ao buscar imagem", e);
		}
	}
}
