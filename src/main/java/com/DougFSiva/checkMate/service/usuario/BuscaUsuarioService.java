package com.DougFSiva.checkMate.service.usuario;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.UsuarioResponse;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaUsuarioService {

	private final UsuarioRepository repository;

	@Cacheable(value = "usuarios", key = "'usuarioID_' + #ID")
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@Transactional(readOnly = true)
	public UsuarioResponse buscarPeloID(Long ID) {
		return new UsuarioResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public UsuarioResponse buscarAutenticado() {
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UsuarioResponse(usuario);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioResponse::new);
	}
	
	@Cacheable(value = "usuarios", key = "'usuarioPeloPerfil_' + #tipoPerfil + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarPeloTipoPerfil(TipoPerfil tipoPerfil, Pageable paginacao) {
		return repository.findByPerfil_Tipo(tipoPerfil, paginacao).map(UsuarioResponse::new);
	}
	
	@Cacheable(value = "usuarios", key = "'todosUsuariosPagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(UsuarioResponse::new);
	}
	
}
