package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CheckListAmbienteResponse;
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
	public CheckListAmbienteResponse buscarPeloID(Long ID) {
		return new CheckListAmbienteResponse(repository.findByIdOrElseThrow(ID));
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente, paginacao).map(CheckListAmbienteResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResponse> buscarPelaDataHoraEncerramento(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraEncerramento(dataInicial, dataFinal, paginacao).map(CheckListAmbienteResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResponse> buscarPeloCheckListStatus(CheckListAmbienteStatus status, Pageable paginacao) {
		return repository.findByStatus(status, paginacao).map(CheckListAmbienteResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListAmbienteResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CheckListAmbienteResponse::new);
	}
}
