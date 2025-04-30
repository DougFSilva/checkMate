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

import com.DougFSiva.checkMate.dto.form.ItemForm;
import com.DougFSiva.checkMate.dto.response.ItemResponse;
import com.DougFSiva.checkMate.service.item.BuscaItemService;
import com.DougFSiva.checkMate.service.item.CriaItemService;
import com.DougFSiva.checkMate.service.item.DeletaItemService;
import com.DougFSiva.checkMate.service.item.EditaItemService;
import com.DougFSiva.checkMate.service.item.SalvaImagemItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/itens")
@RequiredArgsConstructor
public class ItemController {

	private final BuscaItemService buscaItemService;
	private final CriaItemService criaItemService;
	private final DeletaItemService deletaItemService;
	private final EditaItemService editaItemService;
	private final SalvaImagemItemService salvaImagemItemService;
	
	@PostMapping
	public ResponseEntity<ItemResponse> criarItem(@Valid @RequestBody ItemForm form) {
		ItemResponse item = criaItemService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(item.getID())
				.toUri();
		return ResponseEntity.created(uri).body(item);
	}
	
	@DeleteMapping("/{ID}")
	public ResponseEntity<Void> deletarItem(@PathVariable Long ID) {
		deletaItemService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	public ResponseEntity<ItemResponse> editarItem(
			@PathVariable Long ID, 
			@Valid @RequestBody ItemForm form) {
		ItemResponse item = editaItemService.editar(ID, form);
		return ResponseEntity.ok().body(item);
	}
	
	@PostMapping("/imagem/{ID}")
	public ResponseEntity<ItemResponse> salvarImagemDeItem(
			@PathVariable Long ID,
			@RequestParam("file") MultipartFile imagem) {
		ItemResponse item = salvaImagemItemService.salvar(imagem, ID);
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<ItemResponse> buscarItemPeloID(@PathVariable Long ID) {
		ItemResponse item = buscaItemService.buscarPeloID(ID);
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/{compartimentoID}")
	public ResponseEntity<Page<ItemResponse>> buscarItensPeloCompartimento(
			@PathVariable Long compartimentoID,
			Pageable paginacao) {
		Page<ItemResponse> itens = buscaItemService.buscarPeloCompartimento(compartimentoID, paginacao);
		return ResponseEntity.ok().body(itens);
	}
	
	@GetMapping
	public ResponseEntity<Page<ItemResponse>> buscarTodosItens(Pageable paginacao) {
		Page<ItemResponse> itens = buscaItemService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(itens);

	}
	
}
