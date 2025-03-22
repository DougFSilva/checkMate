package com.DougFSiva.checkMate.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

	default CheckList findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Check list com ID %d não encontrado!", ID)));
	}
	
	boolean existsByAmbiente(Ambiente ambiente);
	
	Page<CheckList> findByAmbiente(Ambiente ambiente, Pageable paginacao);
	
	Page<CheckList> findByDataHoraEncerramento(LocalDateTime dataInicial, LocalDateTime dataFinal, Pageable paginacao);
	
	Page<CheckList> findByCheckListStatus(CheckListStatus status, Pageable paginacao);
	
	Page<CheckList> findAll(Pageable paginacao);
}
