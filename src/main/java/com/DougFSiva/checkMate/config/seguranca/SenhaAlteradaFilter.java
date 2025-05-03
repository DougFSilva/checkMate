package com.DougFSiva.checkMate.config.seguranca;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.DougFSiva.checkMate.dto.response.ErroResponse;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SenhaAlteradaFilter extends OncePerRequestFilter {
	
	private final ObjectMapper objectMapper = new ObjectMapper()
	        .registerModule(new JavaTimeModule()) 
	        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

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
	                ErroResponse erro = new ErroResponse(
	        				LocalDateTime.now(), 
	        				HttpStatus.FORBIDDEN.value(), 
	        				"A senha ainda não foi alterada. Por favor, altere sua senha antes de continuar",
	        				request.getRequestURI());
	                String json = objectMapper.writeValueAsString(erro);
	    			response.getWriter().write(json);
	                return;
	            }
	        }

	        filterChain.doFilter(request, response);
		
	}
	
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/auth") || path.startsWith("/usuarios/alterar-senha");
    }

}
