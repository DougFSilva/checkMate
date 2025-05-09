package com.DougFSiva.checkMate.service.usuario;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class CriaUsuarioAdminDefault {
	
	@Value("${user.admin.senha}")
	private String senha;

    private static final LoggerPadrao logger = new LoggerPadrao(CriaUsuarioAdminDefault.class);
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificadorDeSenha;
	
	public void criar() {
		String email = "admin@admin";
		if (!repository.existsByEmail(email)) {
			Usuario admin = new Usuario(
					"admin", 
					null, 
					email, 
					new SenhaDeUsuario(senha, codificadorDeSenha), 
					true, 
					new Perfil(TipoPerfil.ADMIN), 
					LocalDate.of(2100,2 ,2));
			repository.save(admin);
			logger.info("Criado usuário admin default");
		}
	}
}
