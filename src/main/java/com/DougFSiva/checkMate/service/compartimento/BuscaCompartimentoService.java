package com.DougFSiva.checkMate.service.compartimento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.CompartimentoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CompartimentoResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCompartimentoService {

	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	
	@PreAuthorize("isAuthenticated()")
	public CompartimentoDetalhadoResponse buscarPeloID(Long ID) {
		return new CompartimentoDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
	public List<CompartimentoResumoResponse> buscarPeloAmbiente(Long ambienteID) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente).stream().map(CompartimentoResumoResponse::new).collect(Collectors.toList());
	}
	
	@PreAuthorize("isAuthenticated()")
	public Page<CompartimentoResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CompartimentoResumoResponse::new);
	}
}
