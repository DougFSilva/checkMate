package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.service.imagem.SalvaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalvaImagemAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(SalvaImagemAmbienteService.class);

	private final AmbienteRepository repository;
	private final SalvaImagemService salvaImagemService;

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "ambientes", allEntries = true)
	public AmbienteResponse salvar(MultipartFile imagem, Long ID) {
		Ambiente ambiente = repository.findByIdOrElseThrow(ID);
		String nomeImagem = String.format("%s/%d-%s", 
				ImagemConfig.PASTA_IMAGEM_AMBIENTE, 
				ambiente.getID(), 
				ambiente.getDescricao());
		String nomeImagemSalva = salvaImagemService.salvarImagem(imagem, nomeImagem);
		ambiente.setImagem(nomeImagemSalva);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.info(String.format("Alterada imagem de ambiente %s", ambiente.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}

}
