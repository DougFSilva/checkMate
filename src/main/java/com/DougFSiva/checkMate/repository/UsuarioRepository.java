package com.DougFSiva.checkMate.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	default Usuario findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Usuário com ID %d não encontrado!", ID)));
	}
	
	Optional<Usuario> findByEmail(String email);
	
	Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable paginacao);
	
	Page<Usuario> findByPerfil_Tipo(TipoPerfil tipoPerfil, Pageable paginacao);
	
	List<Usuario> findByPerfil_TipoAndDataValidadeBefore(TipoPerfil tipoPerfil, LocalDate data);
	
	boolean existsByEmail(String email);
}
