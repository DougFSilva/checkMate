package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.EditaAmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditaAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(EditaAmbienteService.class);
	private final AmbienteRepository repository;
		
	public AmbienteResponse editar(EditaAmbienteForm form) {
		Ambiente ambiente = repository.findByIdOrElseThrow(form.ID());
		ambiente.setNome(form.nome());
		ambiente.setDescricao(form.descricao());
		ambiente.setLocalizacao(form.localizacao());
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.infoComUsuario(String.format("Editado ambiente %s", ambiente.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}
	
}
