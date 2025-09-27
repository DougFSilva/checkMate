package com.DougFSiva.checkMate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.config.seguranca.TokenService;
import com.DougFSiva.checkMate.dto.form.AlteraSenhaUsuarioForm;
import com.DougFSiva.checkMate.dto.form.LoginForm;
import com.DougFSiva.checkMate.dto.response.AuthResponse;
import com.DougFSiva.checkMate.exception.ContaDeUsuarioExpiradaException;
import com.DougFSiva.checkMate.exception.ErroDeAutenticacaoDeUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.service.usuario.AlteraSenhaDeUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuário")
public class AutenticacaoController {

	private final AuthenticationManager authenticationManager;
	private final AlteraSenhaDeUsuarioService alteraSenhaDeUsuarioService;
	private final TokenService tokenService;
	
	@PostMapping
    @Operation(
    		summary = "Autenticar usuário", 
    		description = "Autentica o usuário e retorna um token JWT para acesso às demais funcionalidades da API."
    				+ "Este token possui as claims perfil, nome e senha alterada, além do email e data de expiração"
    )
	public ResponseEntity<AuthResponse> autenticar(@Valid @RequestBody LoginForm form){
		 try {
		        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(form.email(), form.senha());
		        Authentication authentication = authenticationManager.authenticate(login);
		        Usuario usuario = (Usuario) authentication.getPrincipal();

		        if (!usuario.getSenhaAlterada()) {
		            AuthResponse authResponse = new AuthResponse(null, null, false);
		            return ResponseEntity.ok().body(authResponse);
		        }

		        String token = tokenService.gerarToken(usuario);
		        AuthResponse authResponse = new AuthResponse(token, "Bearer ", true);
		        return ResponseEntity.ok().body(authResponse);

		    } catch(AccountExpiredException e) {
		    	throw new ContaDeUsuarioExpiradaException(
		    			"Usuário com conta expirada. Contate um administrador e renove a data de validade para voltar a ter acesso");
		    }
		 
		 	catch (AuthenticationException e) {
		    	e.printStackTrace();
		        throw new ErroDeAutenticacaoDeUsuarioException("Usuário ou senha inválidos", e);
		    }
	}
	
	@PatchMapping("/alterar-senha")
	@Operation(summary = "Alterar senha", description = "Altera a senha de um usuário existente")
	public ResponseEntity<Void> AlterarSenha(@Valid @RequestBody AlteraSenhaUsuarioForm form) {
		alteraSenhaDeUsuarioService.alterar(form);
		return ResponseEntity.ok().build();
	}
}
