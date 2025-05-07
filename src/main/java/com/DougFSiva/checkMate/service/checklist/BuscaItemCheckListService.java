package com.DougFSiva.checkMate.service.checklist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.ItemCheckListResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaItemCheckListService {

	private final ItemCheckListRepository repository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	
	@Transactional(readOnly = true)
	public ItemCheckListResponse buscarPeloID(Long ID) {
		return new ItemCheckListResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@Transactional(readOnly = true)
	public List<ItemCheckListResponse> buscarPeloCheckListCompartimento(Long checkListCompartimentoID) {
		CheckListCompartimento checkList = checkListCompartimentoRepository.findByIdOrElseThrow(checkListCompartimentoID);
		return repository.findByCheckListCompartimento(checkList)
		.stream()
		.map(ItemCheckListResponse::new)
		.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<ItemCheckListResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(ItemCheckListResponse::new);
	}
}
