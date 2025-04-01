package com.DougFSiva.checkMate.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Emprestimo;
import com.DougFSiva.checkMate.model.Item;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	default Emprestimo findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Empréstimo com ID %d não encontrado!", ID)));
	}
	
	Page<Emprestimo> findByItem(Item item, Pageable paginacao);
	
	Page<Emprestimo> findByItem_Compartimento_Ambiente(Ambiente ambiente, Pageable paginacao);

	Page<Emprestimo> findByDevolvido(boolean devolvido, Pageable paginacao);
	
	Page<Emprestimo> findByDataHoraEmprestimoBetween(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao);

	Page<Emprestimo> findByDataHoraDevolucaoBetween(LocalDate dataInicial, LocalDate dataFinal, Pageable paginacao);

}
