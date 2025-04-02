package com.DougFSiva.checkMate.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimentoStatus;

public interface CheckListAmbienteRepository extends JpaRepository<CheckListAmbiente, Long> {

	default CheckListAmbiente findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Check-list de ambiente com ID %d não encontrado!", ID)));
	}
	
	Page<CheckListAmbiente> findByAmbiente(Ambiente ambiente, Pageable paginacao);
	
	Page<CheckListAmbiente> findByDataHoraEncerramento(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao);
	
	Page<CheckListAmbiente> findByStatus(CheckListCompartimentoStatus status, Pageable paginacao);
	
	boolean existsByAmbiente(Ambiente ambiente);
}
