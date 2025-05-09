package com.DougFSiva.checkMate.service.checklist;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoResumoResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCheckListCompartimentoService {

	private final CheckListCompartimentoRepository repository;
	private final CheckListAmbienteRepository checkListAmbienteRepository;
	
	@Cacheable(value = "checklistsCompartimento", key = "'checklistID_' + #ID")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public CheckListCompartimentoDetalhadoResponse buscarPeloID(Long ID) {
		return new CheckListCompartimentoDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public List<CheckListCompartimentoResumoResponse> buscarPeloCheckListAmbiente(Long checkListAmbienteID) {
		CheckListAmbiente checkListAmbiente = checkListAmbienteRepository.findByIdOrElseThrow(checkListAmbienteID);
		return repository.findByCheckListAmbiente(checkListAmbiente)
				.stream()
				.map(CheckListCompartimentoResumoResponse::new).toList();
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<CheckListCompartimentoResumoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CheckListCompartimentoResumoResponse::new);
	}
}
