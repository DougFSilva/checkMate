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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/checklists-compartimento")
@RequiredArgsConstructor
@Tag(
		name = "CheckLists de Compartimento", 
		description = "Endpoints para gerenciamento de checklists de ambiente"
)
public class CheckListCompartimentoController {
	
	private final PreencheCheckListEntradaService preencheCheckListEntradaService;
	private final PreencheCheckListSaidaService preencheCheckListSaidaService;
	private final BuscaCheckListCompartimentoService buscaCheckListCompartimentoService;

	@PostMapping("/preencher-entrada")
    @Operation(
    		summary = "Preencher checklist de entrada", 
    		description = "Preenche o checklist de entrada para o compartimento especificado."
    )
	public ResponseEntity<Void> preencherCheckListDeEntrada(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListEntradaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/preencher-saida")
    @Operation(
    		summary = "Preencher checklist de saída", 
    		description = "Preenche o checklist de saída para o compartimento especificado."
    )
	public ResponseEntity<Void> preencherCheckListDeSaida(@Valid @RequestBody PreencheCheckListForm form) {
		preencheCheckListSaidaService.preencher(form);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
    @Operation(
    		summary = "Buscar checklist de compartimento por ID", 
    		description = "Retorna o checklist de compartimento pelo seu ID."
    )
	public ResponseEntity<CheckListCompartimentoDetalhadoResponse> buscarCheckListDeCompartimentoPeloID(
			@PathVariable Long ID) {
		CheckListCompartimentoDetalhadoResponse checkList = buscaCheckListCompartimentoService
				.buscarPeloID(ID);
		return ResponseEntity.ok().body(checkList);
	}
	
	@GetMapping("/check-list-ambiente/{checkListAmbienteID}")
    @Operation(
    		summary = "Buscar checklists de compartimento por checklist de ambiente", 
    		description = "Retorna uma lista de checklists de compartimento filtrados pelo ID do checklist de ambiente."
    )
	public ResponseEntity<List<CheckListCompartimentoResumoResponse>> buscarCheckListDeCompartimentoPeloCheckListAmbiente(
			@PathVariable Long checkListAmbienteID) {
		List<CheckListCompartimentoResumoResponse> checkLists = buscaCheckListCompartimentoService
				.buscarPeloCheckListAmbiente(checkListAmbienteID);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping
    @Operation(
    		summary = "Buscar todos os checklists de compartimento", 
    		description = "Retorna todos os checklists de compartimento."
    )
	public ResponseEntity<Page<CheckListCompartimentoResumoResponse>> buscarTodosCheckListsDeCompartimento(
			Pageable paginacao) {
		Page<CheckListCompartimentoResumoResponse> checkLists = buscaCheckListCompartimentoService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(checkLists);

	}
}
