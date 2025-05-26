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

import com.DougFSiva.checkMate.dto.form.AmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.AmbienteResumoResponse;
import com.DougFSiva.checkMate.service.ambiente.BuscaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.CriaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.DeletaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.EditaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.SalvaImagemAmbienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
	
	@RestController
	@RequestMapping(value = "/ambientes")
	@RequiredArgsConstructor
	@Tag(name = "Ambientes", description = "Endpoints para gerenciamento de ambientes")
	public class AmbienteController {
	
		private final BuscaAmbienteService buscaAmbienteService;
		private final CriaAmbienteService criaAmbienteService;
		private final DeletaAmbienteService deletaAmbienteService;
		private final EditaAmbienteService editaAmbienteService;
		private final SalvaImagemAmbienteService salvaImagemAmbienteService;
		
		@PostMapping
	    @Operation(
	    		summary = "Criar ambiente", 
	    		description = "Cria um novo ambiente com os dados fornecidos"
	    )
		public ResponseEntity<AmbienteResumoResponse> criarAmbiente(@Valid @RequestBody AmbienteForm form) {
			AmbienteResumoResponse ambiente = this.criaAmbienteService.criar(form);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(ambiente.getID())
					.toUri();
			return ResponseEntity.created(uri).body(ambiente);
		}
		
		@DeleteMapping("/{ID}")
	    @Operation(summary = "Deletar ambiente", description = "Deleta um ambiente pelo ID")
		public ResponseEntity<Void> deletarAmbiente(@PathVariable Long ID) {
			deletaAmbienteService.deletar(ID);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping("/{ID}")
	    @Operation(
	    		summary = "Editar ambiente", 
	    		description = "Edita um ambiente existente com os novos dados fornecidos"
	    )
		public ResponseEntity<AmbienteResumoResponse> editarAmbiente(
				@PathVariable Long ID,
				@Valid @RequestBody AmbienteForm form) {
			AmbienteResumoResponse ambiente = editaAmbienteService.editar(ID, form);
			return ResponseEntity.ok().body(ambiente);
		}
		
		@PostMapping("/imagem/{ID}")
	    @Operation(
	    		summary = "Salvar imagem", 
	    		description = "Associa uma imagem ao ambiente especificado"
	    )
		public ResponseEntity<AmbienteResumoResponse> salvarImagemDeAmbiente(
				@PathVariable Long ID,
				@RequestParam("file") MultipartFile imagem) {
			AmbienteResumoResponse ambiente = salvaImagemAmbienteService.salvar(imagem, ID);
			return ResponseEntity.ok().body(ambiente);
		}
		
		@GetMapping("/{ID}")
	    @Operation(summary = "Buscar por ID", description = "Retorna um ambiente pelo seu ID")
		public ResponseEntity<AmbienteDetalhadoResponse> buscarAmbientePeloID(@PathVariable Long ID) {
			AmbienteDetalhadoResponse ambiente = buscaAmbienteService.buscarPeloID(ID);
			return ResponseEntity.ok().body(ambiente);
		}
	
		@GetMapping("/nome")
	    @Operation(
	    		summary = "Buscar por nome", 
	    		description = "Retorna uma lista de ambientes que contenham o nome informado"
	    )
		public ResponseEntity<Page<AmbienteResumoResponse>> buscarAmbientesPeloNome(
				@RequestParam String nome,
				Pageable paginacao) {
			Page<AmbienteResumoResponse> ambientes = buscaAmbienteService.buscarPeloNome(nome, paginacao);
			return ResponseEntity.ok().body(ambientes);
		}
	
		@GetMapping
	    @Operation(
	    		summary = "Listar todos",
	    		description = "Retorna todos os ambientes cadastrados"
	    )
		public ResponseEntity<Page<AmbienteResumoResponse>> buscarTodosAmbientes(Pageable paginacao) {
			Page<AmbienteResumoResponse> ambientes = buscaAmbienteService.buscarTodos(paginacao);
			return ResponseEntity.ok().body(ambientes);
		}
		
	}
