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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.form.CompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CompartimentoResumoResponse;
import com.DougFSiva.checkMate.service.compartimento.BuscaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.CriaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.DeletaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.EditaCompartimentoService;
import com.DougFSiva.checkMate.service.compartimento.SalvaImagemCompartimentoService;

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
	private final SalvaImagemCompartimentoService salvaImagemCompartimentoService;
	
	@PostMapping
	public ResponseEntity<CompartimentoDetalhadoResponse> criarCompartimento(@Valid @RequestBody CompartimentoForm form) {
		CompartimentoDetalhadoResponse compartimento = criaCompartimentoService.criar(form);
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
	public ResponseEntity<CompartimentoDetalhadoResponse> editarCompartimento(
			@PathVariable Long ID, 
			@Valid @RequestBody CompartimentoForm form) {
		CompartimentoDetalhadoResponse compartimento = editaCompartimentoService.editar(ID, form);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@PostMapping("/imagem/{ID}")
	public ResponseEntity<CompartimentoDetalhadoResponse> salvarImagemDeCompartimento(
			@PathVariable Long ID,
			@RequestParam("file") MultipartFile imagem) {
		CompartimentoDetalhadoResponse compartimento = salvaImagemCompartimentoService.salvar(imagem, ID);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<CompartimentoDetalhadoResponse> buscarCompartimentoPeloID(@PathVariable Long ID) {
		CompartimentoDetalhadoResponse compartimento = buscaCompartimentoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<List<CompartimentoResumoResponse>> buscarCompartimentosPeloAmbiente(@PathVariable Long ambienteID) {
		List<CompartimentoResumoResponse> compartimentos = buscaCompartimentoService.buscarPeloAmbiente(ambienteID);
		return ResponseEntity.ok().body(compartimentos);
	}
	
	@GetMapping
	public ResponseEntity<Page<CompartimentoResumoResponse>> buscarTodosCompartimentos(Pageable paginacao) {
		Page<CompartimentoResumoResponse> compartimentos = buscaCompartimentoService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(compartimentos);
	}
}
