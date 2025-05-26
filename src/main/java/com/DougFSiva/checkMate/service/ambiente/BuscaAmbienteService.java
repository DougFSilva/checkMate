package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.AmbienteDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.AmbienteResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaAmbienteService {

	private final AmbienteRepository repository;
	private final CompartimentoRepository compartimentoRepository;
	private final ItemRepository itemRepository;
	
	@Cacheable(value = "ambientes", key = "'ambienteID_' + #ID")
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public AmbienteDetalhadoResponse buscarPeloID(Long ID) {
		Ambiente ambiente = repository.findByIdOrElseThrow(ID);
		int compartimentosPorAmbiente = compartimentoRepository.countByAmbiente(ambiente);
		int itensPorAmbiente = itemRepository.countByCompartimento_Ambiente(ambiente);
		return new AmbienteDetalhadoResponse(ambiente, compartimentosPorAmbiente, itensPorAmbiente);
	}
	
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public Page<AmbienteResumoResponse> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao)
				.map(AmbienteResumoResponse::new);
	}
	
	@Cacheable(
			value = "ambientes", 
			key = "'todosAmbientes_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<AmbienteResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(AmbienteResumoResponse::new);
	}
}
