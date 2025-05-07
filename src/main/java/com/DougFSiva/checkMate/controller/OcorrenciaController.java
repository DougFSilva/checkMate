package com.DougFSiva.checkMate.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.form.TrataOcorrenciaForm;
import com.DougFSiva.checkMate.dto.response.OcorrenciaResponse;
import com.DougFSiva.checkMate.service.ocorrencia.BuscaOcorrenciaService;
import com.DougFSiva.checkMate.service.ocorrencia.EncerraOcorrenciaService;
import com.DougFSiva.checkMate.service.ocorrencia.TrataOcorrenciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

	private final TrataOcorrenciaService trataOcorrenciaService;
	private final EncerraOcorrenciaService encerraOcorrenciaService;
	private final BuscaOcorrenciaService buscaOcorrenciaService;
	
	@PostMapping("/tratar/{ID}")
	public ResponseEntity<OcorrenciaResponse> tratarOcorrencia(@Valid @RequestBody TrataOcorrenciaForm form, 
			@PathVariable Long ID) {
		OcorrenciaResponse ocorrencia = trataOcorrenciaService.tratar(ID, form);
		return ResponseEntity.ok().body(ocorrencia);
	}
	
	@PostMapping("/encerrar/{ID}")
	public ResponseEntity<OcorrenciaResponse> encerrarOcorrencia(@PathVariable Long ID) {
		encerraOcorrenciaService.encerrar(ID);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{ID}")
	public ResponseEntity<OcorrenciaResponse> buscarOcorrenciaPeloID(@PathVariable Long ID) {
		OcorrenciaResponse ocorrencia = buscaOcorrenciaService.buscarPeloID(ID);
		return ResponseEntity.ok().body(ocorrencia);
	}
	
	@GetMapping("/data")
	public ResponseEntity<Page<OcorrenciaResponse>> buscarOcorrenciasPelaData(
			@RequestParam("data-inicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataInicial,
			@RequestParam("data-final") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataFinal,
			Pageable paginacao
			){
		Page<OcorrenciaResponse> ocorrencias = buscaOcorrenciaService.buscarPelaDataHora(
				dataInicial, dataFinal, paginacao);
		return ResponseEntity.ok().body(ocorrencias);
	}
	
	@GetMapping("/ambiente/{ambienteID}")
	public ResponseEntity<Page<OcorrenciaResponse>> buscarOcorrenciasPeloAmbiente(
			@PathVariable Long ambienteID,
			Pageable paginacao
			){
		Page<OcorrenciaResponse> ocorrencias = buscaOcorrenciaService.buscarPeloAmbiente(ambienteID, paginacao);
		return ResponseEntity.ok().body(ocorrencias);
	}
	
	@GetMapping
	public ResponseEntity<Page<OcorrenciaResponse>> buscarTodasOcorrencias(Pageable paginacao){
		Page<OcorrenciaResponse> ocorrencias = buscaOcorrenciaService.buscarTodas(paginacao);
		return ResponseEntity.ok().body(ocorrencias);

	}
}
