package com.DougFSiva.checkMate.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.form.PreencheCheckListForm;
import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoResponse;
import com.DougFSiva.checkMate.service.checklist.BuscaCheckListCompartimentoService;
import com.DougFSiva.checkMate.service.checklist.PreencheCheckListEntradaService;
import com.DougFSiva.checkMate.service.checklist.PreencheCheckListSaidaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/checklists-compartimento")
@RequiredArgsConstructor
public class CheckListCompartimentoController {
	
	private final PreencheCheckListEntradaService preencheCheckListEntradaService;
	private final PreencheCheckListSaidaService preencheCheckListSaidaService;
	private final BuscaCheckListCompartimentoService buscaCheckListCompartimentoService;

	@PostMapping("/compartimento/preencher-entrada")
	public ResponseEntity<Void> preencherCheckListDeEntrada(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListEntradaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/compartimento/preencher-saida")
	public ResponseEntity<Void> preencherCheckListDeSaida(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListSaidaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/compartimento/{compartimentoID}")
	public ResponseEntity<CheckListCompartimentoResponse> buscarCheckListDeCompartimentoPeloID(@PathVariable Long compartimentoID) {
		CheckListCompartimentoResponse checkList = buscaCheckListCompartimentoService.buscarPeloID(compartimentoID);
		return ResponseEntity.ok().body(checkList);
	}
	
	@GetMapping("/compartimento/ambiente/{ambienteID}")
	public ResponseEntity<List<CheckListCompartimentoResponse>> buscarCheckListDeCompartimentoPeloAmbiente(@PathVariable Long ambienteID) {
		List<CheckListCompartimentoResponse> checkLists = buscaCheckListCompartimentoService.buscarPeloCheckListAmbiente(ambienteID);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping("/compartimento")
	public ResponseEntity<Page<CheckListCompartimentoResponse>> buscarTodosCheckListsDeCompartimento(Pageable paginacao) {
		Page<CheckListCompartimentoResponse> checkLists = buscaCheckListCompartimentoService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(checkLists);

	}
}
