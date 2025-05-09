package com.DougFSiva.checkMate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DougFSiva.checkMate.config.seguranca.TokenService;
import com.DougFSiva.checkMate.dto.form.LoginForm;
import com.DougFSiva.checkMate.dto.response.TokenResponse;
import com.DougFSiva.checkMate.exception.ErroDeAutenticacaoDeUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;

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
	private final TokenService tokenService;
	
	@PostMapping
    @Operation(summary = "Autenticar usuário", description = "Autentica o usuário e retorna um token JWT para acesso às demais funcionalidades da API.")
	public ResponseEntity<TokenResponse> autenticar(@Valid @RequestBody LoginForm form){
		try {
			UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(form.email(), form.senha());
			Authentication authentication = authenticationManager.authenticate(login);
			Usuario usuario = (Usuario) authentication.getPrincipal();
			String token = tokenService.gerarToken(usuario.getEmail());
			TokenResponse tokenResponse = new TokenResponse(token, "Bearer ", usuario, usuario.getPerfil());
			return ResponseEntity.ok().body(tokenResponse);
		} catch (AuthenticationException e) {
			throw new ErroDeAutenticacaoDeUsuarioException("Usuário ou senha inválidos", e);
		}	
	}
}
