package com.DougFSiva.checkMate.controller;

import java.net.URI;
import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.response.CheckListAmbienteDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CheckListAmbienteResumoResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.service.checklist.AbreCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.BuscaCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.DeletaCheckListAmbiente;
import com.DougFSiva.checkMate.service.checklist.EncerraCheckListAmbienteService;
import com.DougFSiva.checkMate.service.checklist.LiberaCheckListAmbienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/checklists-ambiente")
@RequiredArgsConstructor
@Tag(
		name = "CheckLists de Ambiente", 
		description = "Endpoints para gerenciamento de checklists de ambiente"
)
public class CheckListAmbienteController {

	private final AbreCheckListAmbienteService abreCheckListAmbienteService;
	private final LiberaCheckListAmbienteService liberaCheckListAmbienteService;
	private final EncerraCheckListAmbienteService encerraCheckListAmbienteService;
	private final DeletaCheckListAmbiente deletaCheckListAmbiente;
	private final BuscaCheckListAmbienteService buscaCheckListAmbienteService;
	
	
	@PostMapping("/{ID}")
    @Operation(
    		summary = "Abrir checklist de ambiente", 
    		description = "Abre o checklist para um ambiente especificado pelo ID."
    )
	public ResponseEntity<CheckListAmbienteDetalhadoResponse> abrirCheckListDeAmbiente(@PathVariable Long ID) {
		CheckListAmbienteDetalhadoResponse checkList = abreCheckListAmbienteService.abrir(ID);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/ambiente/{ID}")
				.buildAndExpand(checkList.getID())
				.toUri();
		return ResponseEntity.created(uri).body(checkList);
	}
	
	@PatchMapping("/liberar/{ID}")
    @Operation(
    		summary = "Liberar checklist de ambiente", 
    		description = "Libera o checklist de ambiente após ser realizado o prenchimento de entrada."
    )
	public ResponseEntity<Void> liberarCheckList(@PathVariable Long ID) {
		liberaCheckListAmbienteService.liberarCheckList(ID);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/encerrar/{ID}")
    @Operation(
    		summary = "Encerrar checklist de ambiente", 
    		description = "Encerra o checklist de ambiente."
    )
	public ResponseEntity<Void> encerrarCheckList(@PathVariable Long ID) {
		encerraCheckListAmbienteService.encerrar(ID);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{ID}")
	@Operation(
    		summary = "Deletar checklist de ambiente", 
    		description = "Deleta o checklist de ambiente pelo ID."
    )
	public ResponseEntity<Void> deletaCheckList(@PathVariable Long ID) {
		deletaCheckListAmbiente.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{ID}")
    @Operation(
    		summary = "Buscar checklist de ambiente por ID", 
    		description = "Retorna o checklist de ambiente pelo seu ID."
    )
	public ResponseEntity<CheckListAmbienteDetalhadoResponse> buscarCheckListDeAmbientePeloID(
			@PathVariable Long ID) {
		CheckListAmbienteDetalhadoResponse checkList = buscaCheckListAmbienteService.buscarPeloID(ID);
		return ResponseEntity.ok().body(checkList);
	}

	@GetMapping("/ambiente/{ambienteID}")
    @Operation(
    		summary = "Buscar checklists de ambiente por ambiente", 
    		description = "Retorna uma lista de checklists de ambiente filtrados por ambiente."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
				.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping("/ambiente/{ambienteID}/status")
    @Operation(
    		summary = "Buscar checklists de ambiente por ambiente e pelo status", 
    		description = "Retorna uma lista de checklists de ambiente filtrados por ambiente e status."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePeloAmbienteEStatus(
			@PathVariable Long ambienteID,
			@RequestParam CheckListAmbienteStatus status,

			Pageable paginacao) {
		Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
				.buscarPeloAmbienteEStatus(ambienteID, status, paginacao);
		return ResponseEntity.ok().body(checkLists);
	}
	
	@GetMapping("/ambiente/{ambienteID}/data-hora-encerramento")
    @Operation(
    		summary = "Buscar checklists de ambiente por ambeinte e intervalo de data e hora de encerramento", 
    		description = "Retorna checklists de ambiente filtrados por ambiente e intervalo de data e hora "
    				+ "de encerramento."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePorAmbienteEDataHoraEncerramento(
			@PathVariable Long ambienteID,
	        @RequestParam("data-inicial")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataInicial,
			
	        @RequestParam("data-final") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataFinal,
	        Pageable paginacao) {
	    Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
	    		.buscarPeloAmbienteEDataHoraEncerramento(ambienteID, dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping("/data-hora-abertura")
    @Operation(
    		summary = "Buscar checklists de ambiente por intervalo de data e hora de abertura", 
    		description = "Retorna checklists de ambiente filtrados por intervalo de data e hora de aberturaq."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePorDataHoraAbertura(
	        @RequestParam("data-inicial") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataInicial,
	        
	        @RequestParam("data-final") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataFinal,
	        Pageable paginacao) {
	    Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
	    		.buscarPelaDataHoraAbertura(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping("/data-hora-encerramento")
    @Operation(
    		summary = "Buscar checklists de ambiente por intervalo de data e hora de encerramento", 
    		description = "Retorna checklists de ambiente filtrados por intervalo de data e hora de encerramento."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePorDataHoraEncerramento(
	        @RequestParam("data-inicial") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataInicial,
	        
	        @RequestParam("data-final") 
	        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dataFinal,
	        Pageable paginacao) {
	    Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
	    		.buscarPelaDataHoraEncerramento(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping("/status")
    @Operation(
    		summary = "Buscar checklists de ambiente por status", 
    		description = "Retorna checklists de ambiente filtrados pelo status."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarCheckListsDeAmbientePeloStatus(
	        @RequestParam CheckListAmbienteStatus status,
	        Pageable paginacao) {

	    Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
	    		.buscarPeloCheckListStatus(status, paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
	@GetMapping
    @Operation(
    		summary = "Buscar todos os checklists de ambiente", 
    		description = "Retorna todos os checklists de ambiente."
    )
	public ResponseEntity<Page<CheckListAmbienteResumoResponse>> buscarTodosCheckListsDeAmbiente(
	        Pageable paginacao) {

	    Page<CheckListAmbienteResumoResponse> checkLists = buscaCheckListAmbienteService
	    		.buscarTodos(paginacao);
	    return ResponseEntity.ok(checkLists);
	}
	
}
