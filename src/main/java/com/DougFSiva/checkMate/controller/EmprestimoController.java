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
import com.DougFSiva.checkMate.dto.response.EmprestimoResponse;
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
	public ResponseEntity<EmprestimoResponse> emprestarItem(@Valid @RequestBody EmprestaItemForm form ) {
		EmprestimoResponse emprestimo = emprestaItemService.emprestar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(emprestimo.getID())
				.toUri();
		return ResponseEntity.created(uri).body(emprestimo);
	}
	
	@PostMapping("/devolver/{ID}")
	public ResponseEntity<EmprestimoResponse> devolverItem(@PathVariable Long ID) {
		EmprestimoResponse emprestimo = devolveItemService.devolver(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<EmprestimoResponse> buscarEmprestimoPeloID(@PathVariable Long ID) {
		EmprestimoResponse emprestimo = buscaEmprestimoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/item/{itemID}")
	public ResponseEntity<Page<EmprestimoResponse>> buscarEmprestimosPeloItem(
			@PathVariable Long ID,
			Pageable paginacao) {
		Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarPeloItem(ID, paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<Page<EmprestimoResponse>> buscarEmprestimosPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/status-devolvido/{status}")
	public ResponseEntity<Page<EmprestimoResponse>> buscarEmprestimosPeloStatusDevolvido(
	        @PathVariable boolean status,
	        Pageable paginacao) {

	    Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarPeloStatusDevolvido(status, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-emprestimo")
	public ResponseEntity<Page<EmprestimoResponse>> buscarEmprestimosPelaDataDeEmprestimo(
	        @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeEmprestimo(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-devolucao")
	public ResponseEntity<Page<EmprestimoResponse>> buscarEmprestimosPelaDataDeDevolucao(
	        @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeDevolucao(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping
	public ResponseEntity<Page<EmprestimoResponse>> buscarTodosEmprestimos(Pageable paginacao) {
	    Page<EmprestimoResponse> emprestimos = buscaEmprestimoService.buscarTodos(paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
}
