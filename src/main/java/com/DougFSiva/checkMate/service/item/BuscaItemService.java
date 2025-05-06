package com.DougFSiva.checkMate.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.ItemDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.ItemResumoResponse;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaItemService {

	private final ItemRepository repository;
	private final CompartimentoRepository compartimentoRepository;
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public ItemDetalhadoResponse buscarPeloID(Long ID) {
		return new ItemDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<ItemResumoResponse> buscarPeloCompartimento(Long compartimentoID, Pageable paginacao) {
		Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(compartimentoID);
		return repository.findByCompartimento(compartimento, paginacao).map(ItemResumoResponse::new);
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<ItemResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ItemResumoResponse::new);
	}
}
