package com.DougFSiva.checkMate.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.form.CompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.service.compartimento.BuscaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.CriaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.DeletaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.EditaCompartimentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/compartimentos")
@RequiredArgsConstructor
public class CompartimentoController {

	private final BuscaCompartimentoService buscaCompartimentoService;
	private final CriaCompartimentoService criaCompartimentoService;
	private final DeletaCompartimentoService deletaCompartimentoService;
	private final EditaCompartimentoService editaCompartimentoService;
	
	@PostMapping
	public ResponseEntity<CompartimentoResponse> criarCompartimento(@Valid @RequestBody CompartimentoForm form) {
		CompartimentoResponse compartimento = criaCompartimentoService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(compartimento.getID())
				.toUri();
		return ResponseEntity.created(uri).body(compartimento);
	}
	
	@DeleteMapping("/{ID}")
	public ResponseEntity<Void> deletarCompartimento(@PathVariable Long ID) {
		deletaCompartimentoService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	public ResponseEntity<CompartimentoResponse> editarCompartimento(
			@PathVariable Long ID, 
			@Valid @RequestBody CompartimentoForm form) {
		CompartimentoResponse compartimento = editaCompartimentoService.editar(ID, form);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<CompartimentoResponse> buscarCompartimentoPeloID(@PathVariable Long ID) {
		CompartimentoResponse compartimento = buscaCompartimentoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<List<CompartimentoResponse>> buscarCompartimentosPeloAmbiente(@PathVariable Long ambienteID) {
		List<CompartimentoResponse> compartimentos = buscaCompartimentoService.buscarPeloAmbiente(ambienteID);
		return ResponseEntity.ok().body(compartimentos);
	}
	
	@GetMapping
	public ResponseEntity<Page<CompartimentoResponse>> buscarTodosCompartimentos(Pageable paginacao) {
		Page<CompartimentoResponse> compartimentos = buscaCompartimentoService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(compartimentos);
	}
}
