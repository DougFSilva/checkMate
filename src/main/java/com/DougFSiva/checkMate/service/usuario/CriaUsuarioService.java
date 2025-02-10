package com.DougFSiva.checkMate.service.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.CodificadorDeSenha;
import com.DougFSiva.checkMate.model.Perfil;
import com.DougFSiva.checkMate.model.SenhaDeUsuario;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.service.dto.CriaUsuarioForm;

@Service
public class CriaUsuarioService {
	
    private static final Logger logger = LoggerFactory.getLogger(CriaUsuarioService.class);
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificadorDeSenha;
	
	public CriaUsuarioService(UsuarioRepository repository, CodificadorDeSenha codificadorDeSenha) {
		this.repository = repository;
		this.codificadorDeSenha = codificadorDeSenha;
	}

	public Usuario criar(CriaUsuarioForm form) {
		SenhaDeUsuario senha = new SenhaDeUsuario(form.senha(), codificadorDeSenha);
		Perfil perfil = new Perfil(form.tipoPerfil());
		Usuario usuario = new Usuario(form.nome(), form.CPF(), form.email(), senha, false, perfil, form.foto());
		logger.info(String.format("Usuario %s criado", usuario));
		return repository.save(usuario);
	}
	
}
