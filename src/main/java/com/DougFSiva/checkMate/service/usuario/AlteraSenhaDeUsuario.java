package com.DougFSiva.checkMate.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.SenhaDeUsuarioInvalidaException;
import com.DougFSiva.checkMate.model.CodificadorDeSenha;
import com.DougFSiva.checkMate.model.SenhaDeUsuario;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class AlteraSenhaDeUsuario {

    private static final LoggerPadrao logger = new LoggerPadrao(AlteraSenhaDeUsuario.class);
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificadorDeSenha;
	
	public AlteraSenhaDeUsuario(UsuarioRepository repository, CodificadorDeSenha codificadorDeSenha) {
		this.repository = repository;
		this.codificadorDeSenha = codificadorDeSenha;
	}

	public void alterar(Long ID, String senhaAntiga, String novaSenha) {
		validarUsuarioAutenticado(ID);
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		if (!codificadorDeSenha.comparar(senhaAntiga, usuario.getSenha().getSenha())) {
			throw new SenhaDeUsuarioInvalidaException("A senha antiga não confere com a senha atual do usuário!");
		}
		SenhaDeUsuario senhaDeUsuario = new SenhaDeUsuario(novaSenha, codificadorDeSenha);
		usuario.setSenha(senhaDeUsuario);
		usuario.setSenhaAlterada(true);
		repository.save(usuario);
		logger.infoComUsuario(String.format("Alterar senha de usuário com ID %d!", ID));
	}
	
	 private void validarUsuarioAutenticado(Long ID) {
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String emailUsuarioAutenticado = authentication.getName();
	        Usuario usuarioAutenticado = repository.findByEmail(emailUsuarioAutenticado)
	           .orElseThrow(() -> new SecurityException(String.format("Usuário com email %s não encontrado!", emailUsuarioAutenticado)));
	        if (!usuarioAutenticado.getID().equals(ID)) {
	           throw new SecurityException("Você não tem permissão para editar este usuário!");
	        }
	    }
}
