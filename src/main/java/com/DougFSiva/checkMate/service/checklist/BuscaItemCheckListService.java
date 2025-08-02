package com.DougFSiva.checkMate.service.checklist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.ItemCheckListDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.ItemCheckListResumoResponse;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaItemCheckListService {

	private final ItemCheckListRepository repository;
	private final ItemRepository itemRepository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	
	@Transactional(readOnly = true)
	public ItemCheckListDetalhadoResponse buscarPeloID(Long ID) {
		return new ItemCheckListDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@Transactional(readOnly = true)
	public List<ItemCheckListResumoResponse> buscarPeloCheckListCompartimento(Long checkListCompartimentoID) {
		CheckListCompartimento checkList = checkListCompartimentoRepository.findByIdOrElseThrow(checkListCompartimentoID);
		return repository.findByCheckListCompartimento(checkList)
		.stream()
		.map(ItemCheckListResumoResponse::new)
		.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<ItemCheckListResumoResponse> buscarTodosPeloItem(Long itemID,Pageable paginacao) {
		Item item = itemRepository.findByIdOrElseThrow(itemID);
		return repository.findByItem(item, paginacao).map(ItemCheckListResumoResponse::new);
	}
	
	@Transactional(readOnly = true)
	public Page<ItemCheckListResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ItemCheckListResumoResponse::new);
	}
}
