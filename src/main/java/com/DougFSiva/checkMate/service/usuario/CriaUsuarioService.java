package com.DougFSiva.checkMate.service.usuario;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.UsuarioForm;
import com.DougFSiva.checkMate.dto.response.UsuarioDetalhadoResponse;
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
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA')")
	@CacheEvict(value = "usuarios", allEntries = true)
	public UsuarioDetalhadoResponse criar(UsuarioForm form) {
		validaUsuarioService.validarUnicoEmail(form.email());
		validaUsuarioService.validarUnicoCPF(form.CPF());
		SenhaDeUsuario senha = new SenhaDeUsuario("Ps@" + form.CPF(), codificadorDeSenha);
		Perfil perfil = new Perfil(TipoPerfil.peloNome(form.tipoPerfil()));
		Usuario usuario = new Usuario(form.nome(), form.CPF(), form.email(), senha, false, perfil, form.dataValidade());
		Usuario usuarioSalvo = repository.save(usuario);
		logger.info(String.format("Usuário %s criado", usuarioSalvo.infoParaLog()));
		return new UsuarioDetalhadoResponse(usuarioSalvo);
	}
	
}
