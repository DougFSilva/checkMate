package com.DougFSiva.checkMate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.dto.response.RelatorioResumoGeralResponse;
import com.DougFSiva.checkMate.service.relatorio.RelatorioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

	private final RelatorioService service;
	
	@GetMapping("/resumo-geral")
	public ResponseEntity<RelatorioResumoGeralResponse> buscarRelatorioResumoGeral(){
		return ResponseEntity.ok().body(this.service.buscarRealtorioResumoGeral());
	}
}
