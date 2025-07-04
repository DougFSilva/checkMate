package com.DougFSiva.checkMate.config.seguranca;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.DougFSiva.checkMate.exception.ErroDeAutenticacaoDeUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FiltroAutenticacaoJWT extends OncePerRequestFilter {

	private final TokenService tokenService;

	private final UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		if (tokenService.validarToken(token)) {
			autenticar(token);
		}
		filterChain.doFilter(request, response);

	}

	private void autenticar(String token) {
		String username = tokenService.extrairUsernameDoToken(token);
		Usuario usuario = usuarioRepository.findByEmail(username)
				.orElseThrow(() -> new ErroDeAutenticacaoDeUsuarioException(
						String.format("Usuário com email %s não encontrado", username)));

		UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
	}

	private String recuperarToken(HttpServletRequest request) {
		String headerToken = request.getHeader("Authorization");
		if (headerToken != null && headerToken.startsWith("Bearer ")) {
			return headerToken.substring(7);
		}

		String paramToken = request.getParameter("token");
		return (paramToken != null && !paramToken.isBlank()) ? paramToken : null;
	}

}
