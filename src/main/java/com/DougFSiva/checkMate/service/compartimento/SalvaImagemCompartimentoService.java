package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.imagem.SalvaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalvaImagemCompartimentoService {

    private static final LoggerPadrao logger = new LoggerPadrao(SalvaImagemCompartimentoService.class);

	private final CompartimentoRepository repository;
	private final SalvaImagemService salvaImagemService;
	
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public CompartimentoResponse salvar(MultipartFile imagem, Long ID) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		String nomeImagem = String.format("%s/%d-%s", 
				ImagemConfig.PASTA_IMAGEM_COMPARTIMENTO, 
				compartimento.getID(), 
				compartimento.getDescricao());
		String nomeImagemSalva = salvaImagemService.salvarImagem(imagem, nomeImagem);
		compartimento.setImagem(nomeImagemSalva);
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.info(String.format("Alterada imagem de compartimento %s", compartimento.infoParaLog()));
		return new CompartimentoResponse(compartimentoSalvo);
	}
	
}
