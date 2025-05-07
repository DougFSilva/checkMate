package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CheckListAmbienteDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CheckListAmbienteResumoResponse;
import com.DougFSiva.checkMate.dto.response.CheckListAmbienteResumoSemAmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCheckListAmbienteService {

	private final CheckListAmbienteRepository repository;
	private final AmbienteRepository ambienteRepository;

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public CheckListAmbienteDetalhadoResponse buscarPeloID(Long ID) {
		return new CheckListAmbienteDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResumoSemAmbienteResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente, paginacao).map(CheckListAmbienteResumoSemAmbienteResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResumoResponse> buscarPelaDataHoraEncerramento(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraEncerramentoBetween(dataInicial, dataFinal, paginacao)
				.map(CheckListAmbienteResumoResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResumoResponse> buscarPeloCheckListStatus(CheckListAmbienteStatus status, Pageable paginacao) {
		return repository.findByStatus(status, paginacao).map(CheckListAmbienteResumoResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CheckListAmbienteResumoResponse::new);
	}
}
