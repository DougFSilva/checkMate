package com.DougFSiva.checkMate.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.UsuarioResponse;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

@Service
public class BuscaUsuarioService {

	private final UsuarioRepository repository;

	public BuscaUsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public UsuarioResponse buscarPeloID(Long ID) {
		return new UsuarioResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public Page<UsuarioResponse> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioResponse::new);
	}
	
	public Page<UsuarioResponse> buscarPeloTipoPerfil(TipoPerfil tipoPerfil, Pageable paginacao) {
		return repository.findByPerfilTipoPerfil(tipoPerfil, paginacao).map(UsuarioResponse::new);
	}
	
	public Page<UsuarioResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(UsuarioResponse::new);
	}
	
}
