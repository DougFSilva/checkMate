package com.DougFSiva.checkMate.service.ocorrencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.OcorrenciaDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.OcorrenciaResumoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaOcorrenciaService {

	private final OcorrenciaRepository repository;
	private final CheckListAmbienteRepository checkListAmbienteRepository;
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
	@Transactional(readOnly = true)
	public Page<OcorrenciaResumoResponse> buscarPeloAmbienteEDataHora(
			Long ambienteID, LocalDateTime dataInicial, LocalDateTime dataFinal, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItemCheckList_CheckListCompartimento_CheckListAmbiente_AmbienteAndDataHoraBetween(
				ambiente, dataInicial, dataFinal, paginacao)
				.map(OcorrenciaResumoResponse::new);
	}
	
	public Page<OcorrenciaResumoResponse> buscarPeloStatusEncerrada(boolean encerrada, Pageable paginacao) {
		return repository.findByEncerrada(encerrada, paginacao).map(OcorrenciaResumoResponse::new);
	}	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public List<OcorrenciaResumoResponse> buscarPeloCheckListAmbiente(Long checkListAmbienteID) {
		CheckListAmbiente checkList = checkListAmbienteRepository.findByIdOrElseThrow(checkListAmbienteID);
		return repository.findByItemCheckList_CheckListCompartimento_CheckListAmbiente(checkList)
				.stream()
				.map(OcorrenciaResumoResponse::new)
				.collect(Collectors.toList());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public Page<OcorrenciaResumoResponse> buscarTodas(Pageable paginacao) {
		return repository.findAll(paginacao)
				.map(OcorrenciaResumoResponse::new);
	}

}
