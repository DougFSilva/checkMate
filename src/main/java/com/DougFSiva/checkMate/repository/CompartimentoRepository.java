package com.DougFSiva.checkMate.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Compartimento;

public interface CompartimentoRepository extends JpaRepository<Compartimento, Long>{

	default Compartimento findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Compartimento com ID %d não encontrado!", ID)));
	}
	
	Page<Compartimento> findByAmbiente(Ambiente ambiente, Pageable paginacao);
	
	List<Compartimento> findByAmbiente(Ambiente ambiente);
	
	boolean existsByAmbiente(Ambiente ambiente);
	
	boolean existsByCodigo(String codigo);
	
	int countByAmbiente(Ambiente ambiente);
}
