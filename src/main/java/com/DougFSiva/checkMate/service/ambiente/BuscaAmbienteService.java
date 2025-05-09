package com.DougFSiva.checkMate.service.ambiente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.repository.AmbienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaAmbienteService {

	private final AmbienteRepository repository;
	
	@Cacheable(value = "ambientes", key = "'ambienteID_' + #ID")
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public AmbienteResponse buscarPeloID(Long ID) {
		return new AmbienteResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@Cacheable(value = "ambientes", key = "'ambientesPorNome_' + #nome")
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public List<AmbienteResponse> buscarPeloNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(AmbienteResponse::new)
				.collect(Collectors.toList());
	}
	
	@Cacheable(value = "ambientes", key = "'todosAmbientes'")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public List<AmbienteResponse> buscarTodos() {
		return repository.findAll().stream().map(AmbienteResponse::new).collect(Collectors.toList());
	}
}
