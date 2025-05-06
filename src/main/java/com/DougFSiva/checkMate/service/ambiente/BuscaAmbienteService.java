package com.DougFSiva.checkMate.service.ambiente;

import java.util.List;
import java.util.stream.Collectors;

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
	
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public AmbienteResponse buscarPeloID(Long ID) {
		return new AmbienteResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public List<AmbienteResponse> buscarPeloNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(AmbienteResponse::new)
				.collect(Collectors.toList());
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public List<AmbienteResponse> buscarTodos() {
		return repository.findAll().stream().map(AmbienteResponse::new).collect(Collectors.toList());
	}
}
