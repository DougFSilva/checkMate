package com.DougFSiva.checkMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Compartimento;

public interface CompartimentoRepository extends JpaRepository<Compartimento, Long>{

	default Compartimento findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Compartimento com ID %d não encontrado!", ID)));
	}
}
