package com.DougFSiva.checkMate.service.relatorio;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.RelatorioResumoGeralResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioService {
	
	private final AmbienteRepository ambienteRepository;
	private final CompartimentoRepository compartimentoRepository;
	private final ItemRepository itemRepository;
	private final CheckListAmbienteRepository checkListAmbienteRepository;
	private final OcorrenciaRepository ocorrenciaRepository;
	
	@PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
	public RelatorioResumoGeralResponse buscarRealtorioResumoGeral() {
		long totalAmbientes = this.ambienteRepository.count();
		long totalCompartimentos = this.compartimentoRepository.count();
		long totalItens = itemRepository.count();
		long totalCheckLists = this.checkListAmbienteRepository.countByStatus(CheckListAmbienteStatus.ENCERRADO);
		long totalOCorrencias = ocorrenciaRepository.count();
		return new RelatorioResumoGeralResponse(totalAmbientes, totalCompartimentos, totalItens, totalCheckLists, totalOCorrencias);
	}
	
	
}
