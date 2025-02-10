package com.DougFSiva.checkMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	default Usuario findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Usuário com ID %d não encontrado!", ID)));
	}
}
