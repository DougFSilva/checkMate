package com.DougFSiva.checkMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

	default Ambiente findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Ambiente com ID %d não encontrado!", ID)));
	}
	
	List<Ambiente> findByNomeContainingIgnoreCase(String nome);
	
	boolean existsByGuardioes(Usuario guardiao);
}
