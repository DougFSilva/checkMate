package com.DougFSiva.checkMate.service.usuario;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.UsuarioForm;
import com.DougFSiva.checkMate.dto.response.UsuarioResponse;
import com.DougFSiva.checkMate.exception.UsuarioSemPermissaoException;
import com.DougFSiva.checkMate.model.usuario.Perfil;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditaUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(EditaUsuarioService.class);
	private final UsuarioRepository repository;
	private final ValidaUsuarioService validaUsuarioService;

	@Transactional
	@PreAuthorize("isAuthenticated()")
	@CacheEvict(value = "usuarios", allEntries = true)
	public UsuarioResponse editar(Long ID, UsuarioForm form) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		validarUsuarioAutenticado(usuario);
		if (!form.email().equals(usuario.getEmail())) {
			validaUsuarioService.validarUnicoEmail(form.email());
		}
		if (!form.CPF().equals(usuario.getCPF())) {
			validaUsuarioService.validarUnicoCPF(form.CPF());
		}
		Usuario usuarioAtualizado = atualizarDadosDoUsuario(usuario, form);
		Usuario usuarioSalvo = repository.save(usuarioAtualizado);
		logger.info(String.format("Usuário %s editado para %s!", usuario.infoParaLog(), usuarioSalvo));
		return new UsuarioResponse(usuarioSalvo);
	}
	
	private Usuario atualizarDadosDoUsuario(Usuario usuario, UsuarioForm form) {
		usuario.setNome(form.nome());
		usuario.setCPF(form.CPF());
		usuario.setEmail(form.email());
		usuario.setPerfil(new Perfil(TipoPerfil.peloNome(form.tipoPerfil())));
		usuario.setDataValidade(form.dataValidade());
		return usuario;
	}
	
	private void validarUsuarioAutenticado(Usuario usuario) {
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
	    Usuario usuarioAutenticado = (Usuario) autenticacao.getPrincipal();
	    boolean mesmoUsuario = usuarioAutenticado.getID().equals(usuario.getID());
	    boolean ehAdmin = usuarioAutenticado.getPerfil().getTipo() == TipoPerfil.ADMIN;

	    if (!mesmoUsuario && !ehAdmin) {
	        throw new UsuarioSemPermissaoException("Você não tem permissão para editar este usuário!");
	    }
	}
	
}
