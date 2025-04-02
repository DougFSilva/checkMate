package com.DougFSiva.checkMate.controller;

import java.net.URI;
import java.util.List;

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
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.service.ambiente.AdicionaGuardiaoAoAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.BuscaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.CriaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.DeletaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.EditaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.RemoveGuardiaoDoAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.SalvaImagemAmbienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ambientes")
@RequiredArgsConstructor
public class AmbienteController {

	private AdicionaGuardiaoAoAmbienteService adicionaGuardiaoService;
	private BuscaAmbienteService buscaAmbienteService;
	private CriaAmbienteService criaAmbienteService;
	private DeletaAmbienteService deletaAmbienteService;
	private EditaAmbienteService editaAmbienteService;
	private RemoveGuardiaoDoAmbienteService removeGuardiaoService;
	private SalvaImagemAmbienteService salvaImagemAmbienteService;

	@PostMapping
	public ResponseEntity<AmbienteResponse> criarAmbiente(@Valid @RequestBody AmbienteForm form) {
		AmbienteResponse ambiente = this.criaAmbienteService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(ambiente.getID())
				.toUri();
		return ResponseEntity.created(uri).body(ambiente);
	}
	
	@DeleteMapping("/{ID}")
	public ResponseEntity<Void> deletarAmbiente(@PathVariable Long ID) {
		deletaAmbienteService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	public ResponseEntity<AmbienteResponse> editarAmbiente(
			@PathVariable Long ID,
			@Valid @RequestBody AmbienteForm form) {
		AmbienteResponse ambiente = editaAmbienteService.editar(ID, form);
		return ResponseEntity.ok().body(ambiente);
	}
	
	@PostMapping("/imagem/{ID}")
	public ResponseEntity<AmbienteResponse> salvaImagemDeAmbiente(
			@PathVariable Long ID,
			@RequestParam("file") MultipartFile imagem) {
		AmbienteResponse ambiente = salvaImagemAmbienteService.salvar(imagem, ID);
		return ResponseEntity.ok().body(ambiente);
	}
	
	@PostMapping("/{ID}/guardiao/{guardiaoID}")
	public ResponseEntity<AmbienteResponse> adicionarGuardiaoAoAmbiente(
			@PathVariable Long ID,
			@PathVariable Long guardiaoID){
		AmbienteResponse ambiente = adicionaGuardiaoService.adicionar(guardiaoID, ID);
		return ResponseEntity.ok().body(ambiente);
	}
	
	@DeleteMapping("/{ID}/guardiao/{guardiaoID}")
	public ResponseEntity<AmbienteResponse> removerGuardiaoDoAmbiente(
			@PathVariable Long ID,
			@PathVariable Long guardiaoID) {
		AmbienteResponse ambiente = removeGuardiaoService.remover(guardiaoID, ID);
		return ResponseEntity.ok().body(ambiente);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<AmbienteResponse> buscarAmbientePeloID(@PathVariable Long ID) {
		AmbienteResponse ambiente = buscaAmbienteService.buscarPeloID(ID);
		return ResponseEntity.ok().body(ambiente);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<AmbienteResponse>> buscarAmbientePeloNome(@PathVariable String nome) {
		List<AmbienteResponse> ambientes = buscaAmbienteService.buscarPeloNome(nome);
		return ResponseEntity.ok().body(ambientes);
	}

	@GetMapping
	public ResponseEntity<List<AmbienteResponse>> buscarAmbientePeloNome() {
		List<AmbienteResponse> ambientes = buscaAmbienteService.buscarTodos();
		return ResponseEntity.ok().body(ambientes);
	}

}
