package com.DougFSiva.checkMate.controller;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.form.EmprestaItemForm;
import com.DougFSiva.checkMate.dto.response.EmprestimoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.EmprestimoResumoResponse;
import com.DougFSiva.checkMate.dto.response.EmprestimoResumoSemItemResponse;
import com.DougFSiva.checkMate.service.emprestimo.BuscaEmprestimoService;
import com.DougFSiva.checkMate.service.emprestimo.DevolveItemService;
import com.DougFSiva.checkMate.service.emprestimo.EmprestaItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

	private final EmprestaItemService emprestaItemService;
	private final DevolveItemService devolveItemService;
	private final BuscaEmprestimoService buscaEmprestimoService;
	
	@PostMapping("/emprestar")
	public ResponseEntity<EmprestimoResumoResponse> emprestarItem(@Valid @RequestBody EmprestaItemForm form ) {
		EmprestimoResumoResponse emprestimo = emprestaItemService.emprestar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(emprestimo.getID())
				.toUri();
		return ResponseEntity.created(uri).body(emprestimo);
	}
	
	@PostMapping("/devolver/{ID}")
	public ResponseEntity<EmprestimoResumoResponse> devolverItem(@PathVariable Long ID) {
		EmprestimoResumoResponse emprestimo = devolveItemService.devolver(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<EmprestimoDetalhadoResponse> buscarEmprestimoPeloID(@PathVariable Long ID) {
		EmprestimoDetalhadoResponse emprestimo = buscaEmprestimoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/item/{itemID}")
	public ResponseEntity<Page<EmprestimoResumoSemItemResponse>> buscarEmprestimosPeloItem(
			@PathVariable Long itemID,
			Pageable paginacao) {
		Page<EmprestimoResumoSemItemResponse> emprestimos = buscaEmprestimoService.buscarPeloItem(itemID, paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPeloAmbiente(ambienteID, 
				paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/status-devolvido/{status}")
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPeloStatusDevolvido(
	        @PathVariable boolean status,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPeloStatusDevolvido(status, 
	    		paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-emprestimo")
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPelaDataDeEmprestimo(
	        @RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeEmprestimo(
	    		dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-devolucao")
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPelaDataDeDevolucao(
	        @RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeDevolucao(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarTodosEmprestimos(Pageable paginacao) {
	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarTodos(paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
}
