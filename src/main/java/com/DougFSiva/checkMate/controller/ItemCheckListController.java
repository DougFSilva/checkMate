package com.DougFSiva.checkMate.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.response.ItemCheckListResponse;
import com.DougFSiva.checkMate.service.checklist.BuscaItemCheckListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/item-checklist")
@RequiredArgsConstructor
public class ItemCheckListController {

	private final BuscaItemCheckListService buscaItemCheckListService;

	@GetMapping("/{ID}")
	public ResponseEntity<ItemCheckListResponse> buscarItemCheckListPeloID(@PathVariable Long ID) {
		ItemCheckListResponse item = buscaItemCheckListService.buscarPeloID(ID);
		return ResponseEntity.ok().body(item);
	}

	@GetMapping("/checklist-compartimento/{checkListCompartimentoID}")
	public ResponseEntity<List<ItemCheckListResponse>> buscarItensCheckListPeloCheckListDeCompartimento(
			@PathVariable Long checkListCompartimentoID) {
		List<ItemCheckListResponse> itens = buscaItemCheckListService
				.buscarPeloCheckListCompartimento(checkListCompartimentoID);
		return ResponseEntity.ok().body(itens);
	}

	@GetMapping
	public ResponseEntity<Page<ItemCheckListResponse>> buscarTodosItensCheckList(Pageable paginacao) {
		Page<ItemCheckListResponse> itens = buscaItemCheckListService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(itens);
	}
}
