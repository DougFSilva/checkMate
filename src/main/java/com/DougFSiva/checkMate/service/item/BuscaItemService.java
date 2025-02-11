package com.DougFSiva.checkMate.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.ItemResponse;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

@Service
public class BuscaItemService {

	private final ItemRepository repository;
	private final CompartimentoRepository compartimentoRepository;
	
	public BuscaItemService(ItemRepository repository, CompartimentoRepository compartimentoRepository) {
		this.repository = repository;
		this.compartimentoRepository = compartimentoRepository;
	}
	
	public ItemResponse buscarPeloID(Long ID) {
		return new ItemResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public Page<ItemResponse> buscarPeloCompartimento(Long compartimentoID, Pageable paginacao) {
		Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(compartimentoID);
		return repository.buscarPeloCompartimento(compartimento, paginacao).map(ItemResponse::new);
	}
	
	public Page<ItemResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ItemResponse::new);
	}
}
