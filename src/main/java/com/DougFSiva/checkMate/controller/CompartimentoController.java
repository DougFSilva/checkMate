package com.DougFSiva.checkMate.controller;

import java.net.URI;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/compartimentos")
@RequiredArgsConstructor
@Tag(name = "Compartimentos", description = "Endpoints para gerenciamento de compartimentos")
public class CompartimentoController {

	private final BuscaCompartimentoService buscaCompartimentoService;
	private final CriaCompartimentoService criaCompartimentoService;
	private final DeletaCompartimentoService deletaCompartimentoService;
	private final EditaCompartimentoService editaCompartimentoService;
	private final SalvaImagemCompartimentoService salvaImagemCompartimentoService;
	
	@PostMapping
	@Operation(
			summary = "Criar compartimento", 
			description = "Cria um novo compartimento com as informações fornecidas."
	)
	public ResponseEntity<CompartimentoResumoResponse> criarCompartimento(
			@Valid @RequestBody CompartimentoForm form) {
		CompartimentoResumoResponse compartimento = criaCompartimentoService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(compartimento.getID())
				.toUri();
		return ResponseEntity.created(uri).body(compartimento);
	}
	
	@DeleteMapping("/{ID}")
	@Operation(
			summary = "Deletar compartimento", 
			description = "Deleta o compartimento especificado pelo ID."
	)
	public ResponseEntity<Void> deletarCompartimento(@PathVariable Long ID) {
		deletaCompartimentoService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	@Operation(
			summary = "Editar compartimento", 
			description = "Edita as informações de um compartimento especificado pelo ID."
	)
	public ResponseEntity<CompartimentoResumoResponse> editarCompartimento(
			@PathVariable Long ID, 
			@Valid @RequestBody CompartimentoForm form) {
		CompartimentoResumoResponse compartimento = editaCompartimentoService.editar(ID, form);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@PostMapping("/imagem/{ID}")
	@Operation(
			summary = "Salvar imagem de compartimento", 
			description = "Salva a imagem associada ao compartimento especificado pelo ID."
	)
	public ResponseEntity<CompartimentoResumoResponse> salvarImagemDeCompartimento(
			@PathVariable Long ID,
			@RequestParam("file") MultipartFile imagem) {
		CompartimentoResumoResponse compartimento = salvaImagemCompartimentoService
				.salvar(imagem, ID);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/{ID}")
	@Operation(
			summary = "Buscar compartimento por ID", 
			description = "Retorna as informações detalhadas do compartimento especificado pelo ID."
	)
	public ResponseEntity<CompartimentoDetalhadoResponse> buscarCompartimentoPeloID(@PathVariable Long ID) {
		CompartimentoDetalhadoResponse compartimento = buscaCompartimentoService.buscarPeloID(ID);
		return ResponseEntity.ok().body(compartimento);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	@Operation(
			summary = "Buscar compartimentos por ambiente", 
			description = "Retorna uma lista de compartimentos filtrados pelo ambiente especificado."
	)
	public ResponseEntity<Page<CompartimentoResumoResponse>> buscarCompartimentosPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao) {
		Page<CompartimentoResumoResponse> compartimentos = buscaCompartimentoService
				.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(compartimentos);
	}
	
	@GetMapping
	@Operation(
			summary = "Buscar todos os compartimentos", 
			description = "Retorna todos os compartimentos."
	)
	public ResponseEntity<Page<CompartimentoResumoResponse>> buscarTodosCompartimentos(
			Pageable paginacao) {
		Page<CompartimentoResumoResponse> compartimentos = buscaCompartimentoService
				.buscarTodos(paginacao);
		return ResponseEntity.ok().body(compartimentos);
	}
}
