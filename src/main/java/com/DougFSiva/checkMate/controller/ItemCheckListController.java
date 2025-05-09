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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/itens-checklist")
@RequiredArgsConstructor
@Tag(name = "Itens CheckList", description = "Endpoints para recuperação e consulta de itens de checklist")
public class ItemCheckListController {

	private final BuscaItemCheckListService buscaItemCheckListService;

	@GetMapping("/{ID}")
	@Operation(summary = "Buscar item do checklist por ID", description = "Retorna um item do checklist com base no ID fornecido.")
	public ResponseEntity<ItemCheckListDetalhadoResponse> buscarItemCheckListPeloID(@PathVariable Long ID) {
		ItemCheckListDetalhadoResponse item = buscaItemCheckListService.buscarPeloID(ID);
		return ResponseEntity.ok().body(item);
	}

	@GetMapping("/checklist-compartimento/{checkListCompartimentoID}")
	@Operation(summary = "Buscar itens de checklist por ID de checklist de compartimento", description = "Retorna uma lista de itens de checklist associada a um checklist de compartimento.")
	public ResponseEntity<List<ItemCheckListResumoResponse>> buscarItensCheckListPeloCheckListDeCompartimento(
			@PathVariable Long checkListCompartimentoID) {
		List<ItemCheckListResumoResponse> itens = buscaItemCheckListService
				.buscarPeloCheckListCompartimento(checkListCompartimentoID);
		return ResponseEntity.ok().body(itens);
	}

	@GetMapping
	@Operation(summary = "Buscar todos os itens de checklist", description = "Retorna uma lista de todos os itens de checklist.")
	public ResponseEntity<Page<ItemCheckListResumoResponse>> buscarTodosItensCheckList(Pageable paginacao) {
		Page<ItemCheckListResumoResponse> itens = buscaItemCheckListService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(itens);
	}
}
