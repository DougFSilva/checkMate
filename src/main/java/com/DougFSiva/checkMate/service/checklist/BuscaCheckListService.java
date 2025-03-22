package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.CheckListResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCheckListService {

	private final CheckListRepository repository;
	private final AmbienteRepository ambienteRepository;

	public CheckListResponse buscarPeloID(Long ID) {
		return new CheckListResponse(repository.findByIdOrElseThrow(ID));
	}

	public Page<CheckListResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByAmbiente(ambiente, paginacao).map(CheckListResponse::new);
	}

	public Page<CheckListResponse> buscarPelaDataHoraEncerramento(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraEncerramento(dataInicial, dataFinal, paginacao).map(CheckListResponse::new);
	}

	public Page<CheckListResponse> buscarPeloCheckListStatus(CheckListStatus status, Pageable paginacao) {
		return repository.findByCheckListStatus(status, paginacao).map(CheckListResponse::new);
	}

	public Page<CheckListResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CheckListResponse::new);
	}
}
