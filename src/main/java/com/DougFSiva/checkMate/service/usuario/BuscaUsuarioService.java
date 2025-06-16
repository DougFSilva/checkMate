package com.DougFSiva.checkMate.service.usuario;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.UsuarioDetalhadoResponse;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaUsuarioService {

	private final UsuarioRepository repository;

	@Cacheable(value = "usuarios", key = "'usuarioID_' + #ID")
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public UsuarioDetalhadoResponse buscarPeloID(Long ID) {
		return new UsuarioDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioDetalhadoResponse> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioDetalhadoResponse::new);
	}
	
	@Cacheable(value = "usuarios", key = "'usuarioPeloPerfil_' + #tipoPerfil + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioDetalhadoResponse> buscarPeloTipoPerfil(TipoPerfil tipoPerfil, Pageable paginacao) {
		return repository.findByPerfil_Tipo(tipoPerfil, paginacao).map(UsuarioDetalhadoResponse::new);
	}
	
	@Cacheable(value = "usuarios", key = "'todosUsuariosPagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Page<UsuarioDetalhadoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(UsuarioDetalhadoResponse::new);
	}
	
}
