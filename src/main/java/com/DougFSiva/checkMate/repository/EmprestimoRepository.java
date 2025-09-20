package com.DougFSiva.checkMate.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Emprestimo;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

	default Emprestimo findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Empréstimo com ID %d não encontrado!", ID)));
	}
	
	Page<Emprestimo> findByItem(Item item, Pageable paginacao);
	
	Page<Emprestimo> findByItem_Compartimento_Ambiente(Ambiente ambiente, Pageable paginacao);

	Page<Emprestimo> findByDevolvido(boolean devolvido, Pageable paginacao);
	
	Page<Emprestimo> findByDataHoraEmprestimoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal, Pageable paginacao);

	Page<Emprestimo> findByDataHoraDevolucaoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal, Pageable paginacao);
	
	boolean existsByEmprestador(Usuario usuario);
	
	boolean existsBySolicitante(Usuario usuario);

	boolean existsByRecebedor(Usuario usuario);
	
	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Emprestimo e WHERE e.emprestador = :usuario OR e.solicitante = :usuario OR e.recebedor = :usuario")
	boolean existsByUsuario(@Param("usuario") Usuario usuario);


}
