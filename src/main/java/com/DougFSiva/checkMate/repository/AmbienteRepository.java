package com.DougFSiva.checkMate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

	default Ambiente findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Ambiente com ID %d não encontrado!", ID)));
	}
	
	Page<Ambiente> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
	
	boolean existsByNome(String nome);
}
