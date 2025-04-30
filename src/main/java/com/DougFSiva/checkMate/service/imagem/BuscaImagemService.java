package com.DougFSiva.checkMate.service.imagem;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;

@Service
public class BuscaImagemService {

	@Value("${app.dir.imagens}")
	private String diretorioBase;
	
	@PreAuthorize("isAuthenticated()")
	public Resource buscar(String nomeImagem) {
		 Path path = Path.of(diretorioBase, nomeImagem);
		    try {
		        return new UrlResource(path.toUri());
		    } catch (MalformedURLException e) {
		        throw new ErroDeOperacaoComImagemException("Erro ao buscar imagem", e);
		    }
	}
}
