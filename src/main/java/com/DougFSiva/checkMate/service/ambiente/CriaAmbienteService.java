package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.form.CriaAmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriaAmbienteService {

    private static final LoggerPadrao logger = new LoggerPadrao(CriaAmbienteService.class);
	private final AmbienteRepository repository;
	
	@Transactional
	public AmbienteResponse criar(CriaAmbienteForm form) {
		Ambiente ambiente = new Ambiente(form.nome(), form.descricao(), form.localizacao());
		String imagem = ImagemConfig.getNomeImagemAmbienteDefault();
		ambiente.setImagem(imagem);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.infoComUsuario(String.format("Ambiente %s criado", ambienteSalvo.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}
}
