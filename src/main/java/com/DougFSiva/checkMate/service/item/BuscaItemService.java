package com.DougFSiva.checkMate.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.ItemResponse;
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
	public ItemResponse buscarPeloID(Long ID) {
		return new ItemResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
	public Page<ItemResponse> buscarPeloCompartimento(Long compartimentoID, Pageable paginacao) {
		Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(compartimentoID);
		return repository.findByCompartimento(compartimento, paginacao).map(ItemResponse::new);
	}
	
	@PreAuthorize("isAuthenticated()")
	public Page<ItemResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ItemResponse::new);
	}
}
