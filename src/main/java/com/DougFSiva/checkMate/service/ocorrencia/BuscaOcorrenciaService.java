package com.DougFSiva.checkMate.service.ocorrencia;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.OcorrenciaDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.OcorrenciaResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaOcorrenciaService {

	private final OcorrenciaRepository repository;
	private final AmbienteRepository ambienteRepository;

	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public OcorrenciaDetalhadoResponse buscarPeloID(Long ID) {
		return new OcorrenciaDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public Page<OcorrenciaResumoResponse> buscarPelaDataHora(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository
				.findByDataHoraBetween(dataInicial, dataFinal, paginacao)
				.map(OcorrenciaResumoResponse::new);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public Page<OcorrenciaResumoResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItemCheckList_CheckListCompartimento_CheckListAmbiente_Ambiente(ambiente, paginacao)
				.map(OcorrenciaResumoResponse::new);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public Page<OcorrenciaResumoResponse> buscarTodas(Pageable paginacao) {
		return repository.findAll(paginacao)
				.map(OcorrenciaResumoResponse::new);
	}

}
