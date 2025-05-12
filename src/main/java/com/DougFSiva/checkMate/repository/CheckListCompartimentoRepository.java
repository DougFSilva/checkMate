package com.DougFSiva.checkMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;

public interface CheckListCompartimentoRepository extends JpaRepository<CheckListCompartimento, Long> {

	default CheckListCompartimento findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Check-list de compartimento com ID %d não encontrado!", ID)));
	}
	
	void deleteByCheckListAmbienteIn(List<CheckListAmbiente> checkLists);
	
	List<CheckListCompartimento> findByCheckListAmbiente(CheckListAmbiente checkListAmbiente);
}
