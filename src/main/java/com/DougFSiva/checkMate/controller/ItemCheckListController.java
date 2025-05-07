package com.DougFSiva.checkMate.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.response.ItemCheckListDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.ItemCheckListResumoResponse;
import com.DougFSiva.checkMate.service.checklist.BuscaItemCheckListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/itens-checklist")
@RequiredArgsConstructor
public class ItemCheckListController {

	private final BuscaItemCheckListService buscaItemCheckListService;

	@GetMapping("/{ID}")
	public ResponseEntity<ItemCheckListDetalhadoResponse> buscarItemCheckListPeloID(@PathVariable Long ID) {
		ItemCheckListDetalhadoResponse item = buscaItemCheckListService.buscarPeloID(ID);
		return ResponseEntity.ok().body(item);
	}

	@GetMapping("/checklist-compartimento/{checkListCompartimentoID}")
	public ResponseEntity<List<ItemCheckListResumoResponse>> buscarItensCheckListPeloCheckListDeCompartimento(
			@PathVariable Long checkListCompartimentoID) {
		List<ItemCheckListResumoResponse> itens = buscaItemCheckListService
				.buscarPeloCheckListCompartimento(checkListCompartimentoID);
		return ResponseEntity.ok().body(itens);
	}

	@GetMapping
	public ResponseEntity<Page<ItemCheckListResumoResponse>> buscarTodosItensCheckList(Pageable paginacao) {
		Page<ItemCheckListResumoResponse> itens = buscaItemCheckListService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(itens);
	}
}
