package com.DougFSiva.checkMate.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.response.CheckListAmbienteResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.service.checklist.AbreCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.BuscaCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.EncerraCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.LiberaCheckListAmbienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/checklists-ambiente")
@RequiredArgsConstructor
public class CheckListAmbienteController {

	private final AbreCheckListAmbienteService abreCheckListAmbienteService;
	private final LiberaCheckListAmbienteService liberaCheckListAmbienteService;
	private final EncerraCheckListAmbienteService encerraCheckListAmbienteService;
	private final BuscaCheckListAmbienteService buscaCheckListAmbienteService;
	
	@PostMapping("/{ID}")
	public ResponseEntity<CheckListAmbienteResponse> abrirCheckListDeAmbiente(@PathVariable Long ID) {
		CheckListAmbienteResponse checkList = abreCheckListAmbienteService.abrir(ID);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/ambiente/{ID}")
				.buildAndExpand(checkList.getID())
				.toUri();
		return ResponseEntity.created(uri).body(checkList);
	}
	
	@PostMapping("/liberar/{ID}")
	public ResponseEntity<Void> liberarCheckList(@PathVariable Long ID) {
		liberaCheckListAmbienteService.liberarCheckList(ID);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/encerrar/{ID}")
	public ResponseEntity<Void> encerrarCheckList(@PathVariable Long ID) {
		encerraCheckListAmbienteService.encerrar(ID);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<CheckListAmbienteResponse> buscarCheckListDeAmbientePeloID(@PathVariable Long ID) {
		CheckListAmbienteResponse checkList = buscaCheckListAmbienteService.buscarPeloID(ID);
		return ResponseEntity.ok().body(checkList);
	}

	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<Page<CheckListAmbienteResponse>> buscarCheckListsDeAmbientePeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<CheckListAmbienteResponse> checkLists = buscaCheckListAmbienteService.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping("/data-hora-encerramento")
	public ResponseEntity<Page<CheckListAmbienteResponse>> buscarCheckListsDeAmbientePorDataHoraEncerramento(
	        @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
	        @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
	        Pageable paginacao) {

	    Page<CheckListAmbienteResponse> checkLists = buscaCheckListAmbienteService.buscarPelaDataHoraEncerramento(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<Page<CheckListAmbienteResponse>> buscarCheckListsDeAmbientePeloStatus(
	        @PathVariable CheckListAmbienteStatus status,
	        Pageable paginacao) {

	    Page<CheckListAmbienteResponse> checkLists = buscaCheckListAmbienteService.buscarPeloCheckListStatus(status, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping
	public ResponseEntity<Page<CheckListAmbienteResponse>> buscarTodosCheckListsDeAmbiente(
	        Pageable paginacao) {

	    Page<CheckListAmbienteResponse> checkLists = buscaCheckListAmbienteService.buscarTodos(paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
}
