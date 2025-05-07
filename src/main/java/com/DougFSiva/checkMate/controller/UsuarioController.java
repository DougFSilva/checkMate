package com.DougFSiva.checkMate.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DougFSiva.checkMate.dto.form.AlteraSenhaUsuarioForm;
import com.DougFSiva.checkMate.dto.form.UsuarioForm;
import com.DougFSiva.checkMate.dto.response.UsuarioResponse;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.service.usuario.AlteraSenhaDeUsuarioService;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioService;
import com.DougFSiva.checkMate.service.usuario.CriaUsuarioService;
import com.DougFSiva.checkMate.service.usuario.DeletaUsuarioService;
import com.DougFSiva.checkMate.service.usuario.EditaUsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final AlteraSenhaDeUsuarioService alteraSenhaDeUsuarioService;
	private final BuscaUsuarioService buscaUsuarioService;
	private final CriaUsuarioService criaUsuarioService;
	private final DeletaUsuarioService deletaUsuarioService;
	private final EditaUsuarioService editaUsuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> criaUsuario(@Valid @RequestBody UsuarioForm form) {
		UsuarioResponse usuario = criaUsuarioService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(usuario.getID())
				.toUri();
		return ResponseEntity.created(uri).body(usuario);
	}
	
	@DeleteMapping("/{ID}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long ID) {
		deletaUsuarioService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	public ResponseEntity<UsuarioResponse> editarUsuario(@PathVariable Long ID, 
			@Valid @RequestBody UsuarioForm form) {
		UsuarioResponse usuario = editaUsuarioService.editar(ID, form);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PatchMapping("/alterar-senha")
	public ResponseEntity<Void> AlterarSenha(@Valid @RequestBody AlteraSenhaUsuarioForm form) {
		alteraSenhaDeUsuarioService.alterar(form);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<UsuarioResponse> buscarUsuarioPeloID(@PathVariable Long ID) {
		UsuarioResponse usuario = buscaUsuarioService.buscarPeloID(ID);
		return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Page<UsuarioResponse>> buscarUsuariosPeloNome(@PathVariable String nome, 
			Pageable paginacao){
		Page<UsuarioResponse> usuarios = buscaUsuarioService.buscarPeloNome(nome, paginacao);
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping("/perfil/{perfil}")
	public ResponseEntity<Page<UsuarioResponse>> buscarUsuariosPeloTipoDePerfil(
			@PathVariable TipoPerfil perfil, Pageable paginacao){
		Page<UsuarioResponse> usuarios = buscaUsuarioService.buscarPeloTipoPerfil(perfil, paginacao);
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioResponse>> buscarTodosUsuarios(Pageable paginacao){
		Page<UsuarioResponse> usuarios = buscaUsuarioService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(usuarios);
	}
	
}
