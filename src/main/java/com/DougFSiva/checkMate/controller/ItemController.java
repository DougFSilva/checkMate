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
import com.DougFSiva.checkMate.dto.response.ItemDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.ItemResumoResponse;
import com.DougFSiva.checkMate.service.item.BuscaItemService;
import com.DougFSiva.checkMate.service.item.CriaItemService;
import com.DougFSiva.checkMate.service.item.DeletaItemService;
import com.DougFSiva.checkMate.service.item.EditaItemService;
import com.DougFSiva.checkMate.service.item.SalvaImagemItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/itens")
@RequiredArgsConstructor
@Tag(name = "Itens", description = "Endpoints para genciamento de itens")
public class ItemController {

	private final BuscaItemService buscaItemService;
	private final CriaItemService criaItemService;
	private final DeletaItemService deletaItemService;
	private final EditaItemService editaItemService;
	private final SalvaImagemItemService salvaImagemItemService;
	
	@PostMapping
	@Operation(
			summary = "Criar item", 
			description = "Cria um novo item com as informações fornecidas."
	)
	public ResponseEntity<ItemDetalhadoResponse> criarItem(@Valid @RequestBody ItemForm form) {
		ItemDetalhadoResponse item = criaItemService.criar(form);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{ID}")
				.buildAndExpand(item.getID())
				.toUri();
		return ResponseEntity.created(uri).body(item);
	}
	
	@DeleteMapping("/{ID}")
	@Operation(summary = "Deletar item", description = "Deleta o item especificado pelo ID.")
	public ResponseEntity<Void> deletarItem(@PathVariable Long ID) {
		deletaItemService.deletar(ID);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{ID}")
	@Operation(
			summary = "Editar item", 
			description = "Edita as informações de um item especificado pelo ID."
	)
	public ResponseEntity<ItemDetalhadoResponse> editarItem(
			@PathVariable Long ID, 
			@Valid @RequestBody ItemForm form) {
		ItemDetalhadoResponse item = editaItemService.editar(ID, form);
		return ResponseEntity.ok().body(item);
	}
	
	@PostMapping("/imagem/{ID}")
	@Operation(
			summary = "Salvar imagem de item", 
			description = "Salva a imagem associada ao item especificado pelo ID."
	)
	public ResponseEntity<ItemDetalhadoResponse> salvarImagemDeItem(
			@PathVariable Long ID,
			@RequestParam("file") MultipartFile imagem) {
		ItemDetalhadoResponse item = salvaImagemItemService.salvar(imagem, ID);
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/{ID}")
	@Operation(
			summary = "Buscar item por ID", 
			description = "Retorna as informações detalhadas do item especificado pelo ID."
	)
	public ResponseEntity<ItemDetalhadoResponse> buscarItemPeloID(@PathVariable Long ID) {
		ItemDetalhadoResponse item = buscaItemService.buscarPeloID(ID);
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/compartimento/{compartimentoID}")
	@Operation(
			summary = "Buscar itens por compartimento", 
			description = "Retorna os itens do compartimento especificado, com suporte a paginação."
	)
	public ResponseEntity<Page<ItemResumoResponse>> buscarItensPeloCompartimento(
			@PathVariable Long compartimentoID,
			Pageable paginacao) {
		Page<ItemResumoResponse> itens = buscaItemService.buscarPeloCompartimento(compartimentoID, paginacao);
		return ResponseEntity.ok().body(itens);
	}
	
	@GetMapping
	@Operation(summary = "Buscar todos os itens", description = "Retorna todos os itens.")
	public ResponseEntity<Page<ItemResumoResponse>> buscarTodosItens(Pageable paginacao) {
		Page<ItemResumoResponse> itens = buscaItemService.buscarTodos(paginacao);
		return ResponseEntity.ok().body(itens);

	}
	
}
