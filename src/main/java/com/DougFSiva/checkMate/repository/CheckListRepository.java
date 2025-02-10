package com.DougFSiva.checkMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

	default CheckList findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Check list com ID %d não encontrado!", ID)));
	}
	
	boolean existsByAmbiente(Ambiente ambiente);
}
