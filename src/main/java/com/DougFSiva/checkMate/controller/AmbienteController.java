package com.DougFSiva.checkMate.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.form.CriaAmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.service.ambiente.AdicionaGuardiaoAoAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.BuscaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.CriaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.DeletaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.EditaAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.RemoveGuardiaoDoAmbienteService;
import com.DougFSiva.checkMate.service.ambiente.SalvaImagemAmbienteService;

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
	public ResponseEntity<String> criarAmbiente(@RequestBody CriaAmbienteForm form) {
		AmbienteResponse ambiente = this.criaAmbienteService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(ambiente.getID())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAmbiente(@PathVariable Long ID) {
		deletaAmbienteService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@
}
