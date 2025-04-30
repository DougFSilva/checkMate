package com.DougFSiva.checkMate.config.seguranca;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerificaSenhaAlteradaFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
		
		  if (autenticacao != null 
				  && autenticacao.isAuthenticated() 
				  && autenticacao.getPrincipal() instanceof Usuario usuario) {
	            if (!usuario.getSenhaAlterada()) {
	                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	                response.setContentType("application/json");
	                response.getWriter().write("""
	                        {
	                            "erro": "A senha ainda não foi alterada. Por favor, altere sua senha antes de continuar."
	                        }
	                    """);
	                return;
	            }
	        }

	        filterChain.doFilter(request, response);
		
	}
	
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/auth");
    }

}
