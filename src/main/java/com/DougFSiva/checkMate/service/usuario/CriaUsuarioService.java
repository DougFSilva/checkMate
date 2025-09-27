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
import com.DougFSiva.checkMate.model.usuario.CodificadorDeSenha;
import com.DougFSiva.checkMate.model.usuario.Perfil;
import com.DougFSiva.checkMate.model.usuario.SenhaDeUsuario;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriaUsuarioService {
	
    private static final LoggerPadrao logger = new LoggerPadrao(CriaUsuarioService.class);
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificadorDeSenha;
	private final ValidaUsuarioService validaUsuarioService;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA', 'PROFESSOR')")
	@CacheEvict(value = "usuarios", allEntries = true)
	public UsuarioResponse criar(UsuarioForm form) {
		validarProfessorSoPodeCriarAluno(TipoPerfil.peloNome(form.tipoPerfil()));
		validaUsuarioService.validarUnicoEmail(form.email());
		validaUsuarioService.validarUnicoCPF(form.CPF());
		SenhaDeUsuario senha = new SenhaDeUsuario("Ps@" + form.CPF(), codificadorDeSenha);
		Perfil perfil = new Perfil(TipoPerfil.peloNome(form.tipoPerfil()));
		Usuario usuario = new Usuario(form.nome(), form.CPF(), form.email(), senha, false, perfil, form.dataValidade());
		Usuario usuarioSalvo = repository.save(usuario);
		logger.info(String.format("Usuário %s criado", usuarioSalvo.infoParaLog()));
		return new UsuarioResponse(usuarioSalvo);
	}
	
	private void validarProfessorSoPodeCriarAluno(TipoPerfil perfilDesejado) {
		Usuario usuarioAutenticado = getUsuarioAutenticado();
		TipoPerfil perfilUsuarioAutenticado = usuarioAutenticado.getPerfil().getTipo();
		if (perfilUsuarioAutenticado == TipoPerfil.PROFESSOR && perfilDesejado != TipoPerfil.ALUNO) {
			throw new UsuarioSemPermissaoException(
					"Usuário com perfil PROFESSOR só pode cadastrar ALUNOS!");
		}
	}
	
	private Usuario getUsuarioAutenticado() {
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
		return (Usuario) autenticacao.getPrincipal();
	}
	
}
