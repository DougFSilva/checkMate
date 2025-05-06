package com.DougFSiva.checkMate.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.UsuarioResponse;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaUsuarioService {

	private final UsuarioRepository repository;

	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public UsuarioResponse buscarPeloID(Long ID) {
		return new UsuarioResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioResponse::new);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarPeloTipoPerfil(TipoPerfil tipoPerfil, Pageable paginacao) {
		return repository.findByPerfil_Tipo(tipoPerfil, paginacao).map(UsuarioResponse::new);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(UsuarioResponse::new);
	}
	
}
