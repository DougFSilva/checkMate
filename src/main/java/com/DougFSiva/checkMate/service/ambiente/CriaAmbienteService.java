package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.form.AmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriaAmbienteService {

    private static final LoggerPadrao logger = new LoggerPadrao(CriaAmbienteService.class);
	private final AmbienteRepository repository;
	private final ValidaAmbienteService validaAmbiente;
	
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "ambientes", allEntries = true)
	public AmbienteResumoResponse criar(AmbienteForm form) {
		validaAmbiente.validarUnicoNome(form.nome());
		Ambiente ambiente = new Ambiente(form.nome(), form.descricao(), form.localizacao());
		String imagem = ImagemConfig.getNomeImagemAmbienteDefault();
		ambiente.setImagem(imagem);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.info(String.format("Ambiente %s criado", ambienteSalvo.infoParaLog()));
		return new AmbienteResumoResponse(ambienteSalvo);
	}
	
}
