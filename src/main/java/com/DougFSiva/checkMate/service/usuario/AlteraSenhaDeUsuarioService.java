package com.DougFSiva.checkMate.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.AlteraSenhaUsuarioForm;
import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.exception.SenhaDeUsuarioInvalidaException;
import com.DougFSiva.checkMate.model.usuario.CodificadorDeSenha;
import com.DougFSiva.checkMate.model.usuario.SenhaDeUsuario;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlteraSenhaDeUsuarioService {

    private static final LoggerPadrao logger = new LoggerPadrao(AlteraSenhaDeUsuarioService.class);
	private final UsuarioRepository repository;
	private final CodificadorDeSenha codificadorDeSenha;
	
	@Transactional
	public void alterar(AlteraSenhaUsuarioForm form) {
		Usuario usuario = buscarUsuarioAutenticado();
		if (!codificadorDeSenha.comparar(form.senhaAtual(), usuario.getSenha().getSenha())) {
			throw new SenhaDeUsuarioInvalidaException("A senha antiga não confere com a senha atual do usuário!");
		}
		SenhaDeUsuario senhaDeUsuario = new SenhaDeUsuario(form.novaSenha(), codificadorDeSenha);
		usuario.setSenha(senhaDeUsuario);
		usuario.setSenhaAlterada(true);
		repository.save(usuario);
		logger.info(String.format("Alterada senha do usuário %s!", usuario.infoParaLog()));
	}
	
	private Usuario buscarUsuarioAutenticado() {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String emailUsuarioAutenticado = authentication.getName();
		    return repository.findByEmail(emailUsuarioAutenticado)
			           .orElseThrow(() -> new ObjetoNaoEncontradoException(
			        		   String.format("Usuário com email %s não encontrado!", emailUsuarioAutenticado)));
	}
	
}
