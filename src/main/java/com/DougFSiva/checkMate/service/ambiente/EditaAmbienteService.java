package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.AmbienteForm;
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
	private final ValidaAmbienteService validaAmbiente;
		
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "ambientes", allEntries = true)
	public AmbienteResponse editar(Long ID, AmbienteForm form) {
		Ambiente ambiente = repository.findByIdOrElseThrow(ID);
		if (!form.nome().equals(ambiente.getNome())) {
			validaAmbiente.validarUnicoNome(form.nome());
		}
		ambiente.setNome(form.nome());
		ambiente.setDescricao(form.descricao());
		ambiente.setLocalizacao(form.localizacao());
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.info(String.format("Editado ambiente %s", ambiente.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}
	
}
