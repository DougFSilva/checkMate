package com.DougFSiva.checkMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	default Emprestimo findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Empréstimo com ID %d não encontrado!", ID)));
	}
}
