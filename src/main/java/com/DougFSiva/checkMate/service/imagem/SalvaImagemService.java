package com.DougFSiva.checkMate.service.imagem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;

@Service
public class SalvaImagemService {

	@Value("${app.dir.imagens}")
	private String diretorioBase;

	@Value("${app.imagem.max.file-size}")
	private int tamanhoImagem;

	private static final Set<String> TIPOS_PERMITIDOS = Set.of("image/jpeg", "image/png");

	@PreAuthorize("hasRole('ADMIN')")
	public String salvarImagem(MultipartFile imagem, String nomeImagem) {
		validarImagemNaoVazia(imagem);
		validarTamanhoDaImagem(imagem);
		validarFormatoDaImagem(imagem);
		nomeImagem = removerExtensaoDoNomeDaImagem(nomeImagem.strip());
		nomeImagem = normalizarNome(nomeImagem);
		String extensao = obterExtensaoPorMimeType(imagem);
		nomeImagem = nomeImagem + extensao;
		Path caminhoDestino = Path.of(diretorioBase, nomeImagem);
		try {
			Files.createDirectories(caminhoDestino.getParent());
			Files.copy(imagem.getInputStream(), caminhoDestino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new ErroDeOperacaoComImagemException("Erro ao salvar a imagem.", e);
		}
		return nomeImagem;
	}

	private void validarImagemNaoVazia(MultipartFile imagem) {
		if (imagem == null || imagem.isEmpty()) {
			throw new ErroDeOperacaoComImagemException("O arquivo da imagem não pode estar vazio.");
		}
	}

	private void validarTamanhoDaImagem(MultipartFile imagem) {
		if (imagem.getSize() > tamanhoImagem * 1024 * 1024) {
			throw new ErroDeOperacaoComImagemException(
					String.format("O arquivo não pode exceder %dMB.", tamanhoImagem));
		}
	}

	private void validarFormatoDaImagem(MultipartFile imagem) {
		String contentType = imagem.getContentType();
		if (contentType == null || !TIPOS_PERMITIDOS.contains(contentType)) {
			throw new ErroDeOperacaoComImagemException("Formato de imagem não suportado. Apenas JPEG e PNG.");
		}
	}

	private String obterExtensaoPorMimeType(MultipartFile imagem) {
		String extensao = switch (imagem.getContentType()) {
		case "image/jpeg" -> ".jpg";
		case "image/png" -> ".png";
		default -> null;
		};
		if (extensao == null) {
			throw new ErroDeOperacaoComImagemException("Não foi possível determinar a extensão do arquivo.");
		}
		return extensao;
	}

	private String removerExtensaoDoNomeDaImagem(String nomeImagem) {
		int index = nomeImagem.lastIndexOf('.');
		return (index > 0) ? nomeImagem.substring(0, index) : nomeImagem;
	}

	private String normalizarNome(String nome) {
		String nomeNormalizado = nome.toLowerCase().replaceAll("\\s+", "_").replaceAll("[^a-z0-9_.\\-/]", "");

		if (nomeNormalizado.isBlank()) {
			throw new ErroDeOperacaoComImagemException(
					"O nome do arquivo não pode conter apenas caracteres inválidos.");
		}

		return nomeNormalizado;
	}

}
