package com.DougFSiva.checkMate.service.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.resposta.UsuarioResposta;
import com.DougFSiva.checkMate.model.TipoPerfil;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

@Service
public class BuscaUsuario {

	private final UsuarioRepository repository;

	public BuscaUsuario(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public UsuarioResposta buscarPeloID(Long ID) {
		return new UsuarioResposta(repository.findByIdOrElseThrow(ID));
	}
	
	public Page<UsuarioResposta> buscarPeloNome(String nome, Pageable paginacao) {
		return repository.findByNomeContainingIgnoreCase(nome, paginacao).map(UsuarioResposta::new);
	}
	
	public Page<UsuarioResposta> buscarPeloTipoPerfil(TipoPerfil tipoPerfil, Pageable paginacao) {
		return repository.findByPerfilTipoPerfil(tipoPerfil, paginacao).map(UsuarioResposta::new);
	}
	
	public Page<UsuarioResposta> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(UsuarioResposta::new);
	}
	
}
