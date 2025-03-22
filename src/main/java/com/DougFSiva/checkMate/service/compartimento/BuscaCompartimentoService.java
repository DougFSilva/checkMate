package com.DougFSiva.checkMate.service.compartimento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCompartimentoService {

	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	
	public CompartimentoResponse buscarPeloID(Long ID) {
		return new CompartimentoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public List<CompartimentoResponse> buscarPeloAmbiente(Long ambienteID) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente).stream().map(CompartimentoResponse::new).collect(Collectors.toList());
	}
	
	public Page<CompartimentoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CompartimentoResponse::new);
	}
}
