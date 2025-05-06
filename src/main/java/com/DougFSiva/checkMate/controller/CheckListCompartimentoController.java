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
import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoResumoResponse;
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

	@PostMapping("/preencher-entrada")
	public ResponseEntity<Void> preencherCheckListDeEntrada(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListEntradaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/preencher-saida")
	public ResponseEntity<Void> preencherCheckListDeSaida(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListSaidaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<CheckListCompartimentoDetalhadoResponse> buscarCheckListDeCompartimentoPeloID(@PathVariable Long ID) {
		CheckListCompartimentoDetalhadoResponse checkList = buscaCheckListCompartimentoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(checkList);
	}
	
	@GetMapping("/check-list-ambiente/{checkListAmbienteID}")
	public ResponseEntity<List<CheckListCompartimentoResumoResponse>> buscarCheckListDeCompartimentoPeloCheckListAmbiente(@PathVariable Long checkListAmbienteID) {
		List<CheckListCompartimentoResumoResponse> checkLists = buscaCheckListCompartimentoService.buscarPeloCheckListAmbiente(checkListAmbienteID);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping
	public ResponseEntity<Page<CheckListCompartimentoResumoResponse>> buscarTodosCheckListsDeCompartimento(Pageable paginacao) {
		Page<CheckListCompartimentoResumoResponse> checkLists = buscaCheckListCompartimentoService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(checkLists);

	}
}
