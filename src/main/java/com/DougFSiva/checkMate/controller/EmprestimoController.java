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
import com.DougFSiva.checkMate.service.emprestimo.BuscaEmprestimoService;
import com.DougFSiva.checkMate.service.emprestimo.DevolveItemService;
import com.DougFSiva.checkMate.service.emprestimo.EmprestaItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/emprestimos")
@RequiredArgsConstructor
@Tag(name = "Empréstimos", description = "Endpoints para gerenciamento de empréstimos de itens")
public class EmprestimoController {

	private final EmprestaItemService emprestaItemService;
	private final DevolveItemService devolveItemService;
	private final BuscaEmprestimoService buscaEmprestimoService;
	
	@PostMapping("/emprestar")
	@Operation(summary = "Emprestar item", description = "Realiza o empréstimo de um item.")
	public ResponseEntity<EmprestimoResumoResponse> emprestarItem(@Valid @RequestBody EmprestaItemForm form ) {
		EmprestimoResumoResponse emprestimo = emprestaItemService.emprestar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(emprestimo.getID())
				.toUri();
		return ResponseEntity.created(uri).body(emprestimo);
	}
	
	@PostMapping("/devolver/{ID}")
	@Operation(
			summary = "Devolver item", 
			description = "Devolve o item emprestado especificado pelo ID."
	)
	public ResponseEntity<EmprestimoResumoResponse> devolverItem(@PathVariable Long ID) {
		EmprestimoResumoResponse emprestimo = devolveItemService.devolver(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/{ID}")
	@Operation(
			summary = "Buscar empréstimo por ID", 
			description = "Retorna as informações detalhadas do empréstimo pelo ID."
	)
	public ResponseEntity<EmprestimoDetalhadoResponse> buscarEmprestimoPeloID(@PathVariable Long ID) {
		EmprestimoDetalhadoResponse emprestimo = buscaEmprestimoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(emprestimo);
	}
	
	@GetMapping("/item/{itemID}")
	@Operation(
			summary = "Buscar empréstimos por item", 
			description = "Retorna todos os empréstimos feitos de um item específico."
	)
	public ResponseEntity<Page<EmprestimoDetalhadoResponse>> buscarEmprestimosPeloItem(
			@PathVariable Long itemID,
			Pageable paginacao) {
		Page<EmprestimoDetalhadoResponse> emprestimos = buscaEmprestimoService.buscarPeloItem(itemID, paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	@Operation(
			summary = "Buscar empréstimos por ambiente", 
			description = "Retorna todos os empréstimos realizados em um ambiente específico."
	)
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPeloAmbiente(ambienteID, 
				paginacao);
		return ResponseEntity.ok().body(emprestimos);
	}
	
	@GetMapping("/status-devolvido/{status}")
	@Operation(
			summary = "Buscar empréstimos pelo status de devolução", 
			description = "Retorna empréstimos com base no status de devolução (devolvido ou não)."
	)
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPeloStatusDevolvido(
	        @PathVariable boolean status,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPeloStatusDevolvido(status, 
	    		paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-emprestimo")
	@Operation(
			summary = "Buscar empréstimos por data de empréstimo", 
			description = "Retorna empréstimos realizados dentro de um intervalo de datas."
	)
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPelaDataDeEmprestimo(
	        @RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeEmprestimo(
	    		dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping("/data-devolucao")
	@Operation(
			summary = "Buscar empréstimos por data de devolução", 
			description = "Retorna empréstimos devolvidos dentro de um intervalo de datas."
	)
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarEmprestimosPelaDataDeDevolucao(
	        @RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
	        @RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
	        Pageable paginacao) {

	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarPelaDataDeDevolucao(dataInicial, dataFinal, paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
	
	@GetMapping
	@Operation(summary = "Buscar todos os empréstimos", description = "Retorna todos os empréstimos.")
	public ResponseEntity<Page<EmprestimoResumoResponse>> buscarTodosEmprestimos(Pageable paginacao) {
	    Page<EmprestimoResumoResponse> emprestimos = buscaEmprestimoService.buscarTodos(paginacao);
	    return ResponseEntity.ok(emprestimos);
	}
}
