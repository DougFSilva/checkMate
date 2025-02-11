package com.DougFSiva.checkMate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	default Item findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Item com ID %d não encontrado!", ID)));
	}
	
	Page<Item> buscarPeloCompartimento(Compartimento compartimento, Pageable paginacao);
	
	boolean existsByCompartimento(Compartimento compartimento);
}
