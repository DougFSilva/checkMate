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
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@CacheEvict(value = "usuarios", allEntries = true)
	public UsuarioResponse editar(Long ID, UsuarioForm form) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		validarPermissaoDeEdicao(usuario);
		validarPermissaoDeAlteracaoDePerfil(usuario, TipoPerfil.peloNome(form.tipoPerfil()));
		if (!form.email().equals(usuario.getEmail())) {
			validaUsuarioService.validarUnicoEmail(form.email());
		}
		if (!form.CPF().equals(usuario.getCPF())) {
			validaUsuarioService.validarUnicoCPF(form.CPF());
		}
		Usuario usuarioAtualizado = atualizarDadosDoUsuario(usuario, form);
		Usuario usuarioSalvo = repository.save(usuarioAtualizado);
		logger.info(String.format("Usuário %s editado", usuario.infoParaLog()));
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

	private void validarPermissaoDeEdicao(Usuario usuario) {
		Usuario usuarioAutenticado = getUsuarioAutenticado();
		TipoPerfil perfilUsuarioAutenticado = usuarioAutenticado.getPerfil().getTipo();
		boolean mesmoUsuario = usuarioAutenticado.getID().equals(usuario.getID());
		if (perfilUsuarioAutenticado == TipoPerfil.ADMIN 
				|| perfilUsuarioAutenticado == TipoPerfil.SISTEMA
				|| mesmoUsuario) return;
		TipoPerfil perfilUsuario = usuario.getPerfil().getTipo();
		if (perfilUsuario != TipoPerfil.ALUNO) {
			throw new UsuarioSemPermissaoException(
					"Usuário com perfil PROFESSOR só pode alterar o próprio usuário ou usuário ALUNO!");
		}
	}
	
	private void validarPermissaoDeAlteracaoDePerfil(Usuario usuario, TipoPerfil perfilDesejado) {
		Usuario usuarioAutenticado = getUsuarioAutenticado();
		TipoPerfil perfilUsuarioAutenticado = usuarioAutenticado.getPerfil().getTipo();
		TipoPerfil perfilUsuario = usuario.getPerfil().getTipo();
		if (perfilUsuarioAutenticado == TipoPerfil.PROFESSOR 
				&& perfilUsuario == TipoPerfil.ALUNO 
				&& perfilDesejado != TipoPerfil.ALUNO) {
			throw new UsuarioSemPermissaoException(
					"Usuário com perfil PROFESSOR não pode alterar o perfil do ALUNO!");
		}
	}
	
	private Usuario getUsuarioAutenticado() {
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) autenticacao.getPrincipal();
	}

}
