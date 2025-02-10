package com.DougFSiva.checkMate.service.ambiente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.repository.AmbienteRepository;

@Service
public class BuscaAmbienteService {

	private final AmbienteRepository repository;
	
	public BuscaAmbienteService(AmbienteRepository repository) {
		this.repository = repository;
	}

	public AmbienteResponse buscarPeloID(Long ID) {
		return new AmbienteResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public List<AmbienteResponse> buscarPeloNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(AmbienteResponse::new)
				.collect(Collectors.toList());
	}
	
	public List<AmbienteResponse> buscarTodos() {
		return repository.findAll().stream().map(AmbienteResponse::new).collect(Collectors.toList());
	}
}
