package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CompartimentoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CompartimentoResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCompartimentoService {

	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ItemRepository itemRepository;
	
	@Cacheable(value = "compartimentos", key = "'compartimentoID_' + #ID")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public CompartimentoDetalhadoResponse buscarPeloID(Long ID) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		int itensPorCompartimento = itemRepository.countByCompartimento(compartimento);
		return new CompartimentoDetalhadoResponse(compartimento, itensPorCompartimento);
	}
	
	@Cacheable(value = "compartimentos", key = "'compartimentosPeloAmbiente_' + #ambienteID")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CompartimentoResumoResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente, paginacao).map(CompartimentoResumoResponse::new);
	}
	
	@Cacheable(value = "compartimentos", key = "'todosCompartimentosPagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CompartimentoResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CompartimentoResumoResponse::new);
	}
}
