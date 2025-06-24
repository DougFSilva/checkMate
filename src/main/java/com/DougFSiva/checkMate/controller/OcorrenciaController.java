package com.DougFSiva.checkMate.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.form.TrataOcorrenciaForm;
import com.DougFSiva.checkMate.dto.response.OcorrenciaDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.OcorrenciaResumoResponse;
import com.DougFSiva.checkMate.service.ocorrencia.BuscaOcorrenciaService;
import com.DougFSiva.checkMate.service.ocorrencia.EncerraOcorrenciaService;
import com.DougFSiva.checkMate.service.ocorrencia.TrataOcorrenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ocorrencias")
@RequiredArgsConstructor
@Tag(name = "Ocorrências", description = "Endpoints para gerenciamento de ocorrências")
public class OcorrenciaController {

	private final TrataOcorrenciaService trataOcorrenciaService;
	private final EncerraOcorrenciaService encerraOcorrenciaService;
	private final BuscaOcorrenciaService buscaOcorrenciaService;
	
	@PatchMapping("/tratar/{ID}")
	@Operation(
			summary = "Tratar ocorrência", 
			description = "Trata uma ocorrência existente com as informações fornecidas."
	)
	public ResponseEntity<Void> tratarOcorrencia(@Valid @RequestBody TrataOcorrenciaForm form, 
			@PathVariable Long ID) {
		trataOcorrenciaService.tratar(ID, form);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/encerrar/{ID}")
	@Operation(
			summary = "Encerrar ocorrência", 
			description = "Encerra uma ocorrência com base no ID fornecido."
	)
	public ResponseEntity<OcorrenciaResumoResponse> encerrarOcorrencia(@PathVariable Long ID) {
		encerraOcorrenciaService.encerrar(ID);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
	@Operation(
			summary = "Buscar ocorrência por ID", 
			description = "Busca uma ocorrência específica pelo seu ID."
	)
	public ResponseEntity<OcorrenciaDetalhadoResponse> buscarOcorrenciaPeloID(@PathVariable Long ID) {
		OcorrenciaDetalhadoResponse ocorrencia = buscaOcorrenciaService.buscarPeloID(ID);
		return ResponseEntity.ok().body(ocorrencia);
	}
	
	@GetMapping("/data")
	@Operation(
			summary = "Buscar ocorrências por data", 
			description = "Busca todas as ocorrências dentro de um intervalo de datas."
	)
	public ResponseEntity<Page<OcorrenciaResumoResponse>> buscarOcorrenciasPelaData(
			@RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataInicial,
			@RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataFinal,
			Pageable paginacao
			){
		Page<OcorrenciaResumoResponse> ocorrencias = buscaOcorrenciaService.buscarPelaDataHora(
				dataInicial, dataFinal, paginacao);
		return ResponseEntity.ok().body(ocorrencias);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	@Operation(
			summary = "Buscar ocorrências por ambiente", 
			description = "Retorna todas as ocorrências registradas em um ambiente específico."
	)
	public ResponseEntity<Page<OcorrenciaResumoResponse>> buscarOcorrenciasPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao
			){
		Page<OcorrenciaResumoResponse> ocorrencias = buscaOcorrenciaService.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(ocorrencias);
	}
	
	@GetMapping
	@Operation(
			summary = "Buscar todas as ocorrências", 
			description = "Lista todas as ocorrências registradas."
	)
	public ResponseEntity<Page<OcorrenciaResumoResponse>> buscarTodasOcorrencias(Pageable paginacao){
		Page<OcorrenciaResumoResponse> ocorrencias = buscaOcorrenciaService.buscarTodas(paginacao);
		return ResponseEntity.ok().body(ocorrencias);

	}
}
