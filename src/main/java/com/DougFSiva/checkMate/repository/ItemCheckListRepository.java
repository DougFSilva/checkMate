package com.DougFSiva.checkMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.ItemCheckList;

public interface ItemCheckListRepository extends JpaRepository<ItemCheckList, Long> {

	default ItemCheckList findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Item status com ID %d não encontrado!", ID)));
	}
}
