package com.DougFSiva.checkMate.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.DougFSiva.checkMate.dto.response.ErroResponse;
import com.DougFSiva.checkMate.exception.EmailInvalidoException;
import com.DougFSiva.checkMate.exception.ErroDeAutenticacaoDeUsuarioException;
import com.DougFSiva.checkMate.exception.ErroDeMQTTException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComAmbienteException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCompartimentoException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComEmprestimoException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComImagemException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComOcorrenciaException;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.exception.SenhaDeUsuarioInvalidaException;
import com.DougFSiva.checkMate.exception.SenhaNaoAlteradaException;
import com.DougFSiva.checkMate.exception.UsuarioSemPermissaoException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(EmailInvalidoException.class)
	public ResponseEntity<ErroResponse> emailInvalidoException(EmailInvalidoException e, 
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeAutenticacaoDeUsuarioException.class)
	public ResponseEntity<ErroResponse> erroDeAutenticacaoDeUsuarioException(
			ErroDeAutenticacaoDeUsuarioException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.UNAUTHORIZED.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
	}

	@ExceptionHandler(ErroDeMQTTException.class)
	public ResponseEntity<ErroResponse> erroDeMQTTException(ErroDeMQTTException e, 
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComAmbienteException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComAmbienteException(
			ErroDeOperacaoComAmbienteException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComCheckListException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComCheckListException(
			ErroDeOperacaoComCheckListException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComCompartimentoException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComCompartimentoException(
			ErroDeOperacaoComCompartimentoException e, HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComEmprestimoException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComEmprestimoException(
			ErroDeOperacaoComEmprestimoException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComImagemException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComImagemException(
			ErroDeOperacaoComImagemException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComOcorrenciaException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComOcorrenciaException(
			ErroDeOperacaoComOcorrenciaException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(ErroDeOperacaoComUsuarioException.class)
	public ResponseEntity<ErroResponse> erroDeOperacaoComUsuarioException(
			ErroDeOperacaoComUsuarioException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ErroResponse> objetoNaoEncontradoException(
			ObjetoNaoEncontradoException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.NOT_FOUND.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(SenhaDeUsuarioInvalidaException.class)
	public ResponseEntity<ErroResponse> senhaDeUsuarioInvalidaException(
			SenhaDeUsuarioInvalidaException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	
	@ExceptionHandler(UsuarioSemPermissaoException.class)
	public ResponseEntity<ErroResponse> usuarioSemPermissaoException(UsuarioSemPermissaoException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.FORBIDDEN.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	@ExceptionHandler(SenhaNaoAlteradaException.class)
	public ResponseEntity<ErroResponse> senhaNaoAlteradaException(SenhaNaoAlteradaException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.FORBIDDEN.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResponse> methodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		 StringBuilder erros = new StringBuilder();

		    e.getBindingResult().getAllErrors().forEach(error -> {
		        String msg = error.getDefaultMessage();
		        erros.append(msg).append(" ");
		    });
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				erros.toString(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErroResponse> illegalArgumentException(IllegalArgumentException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroResponse> methodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.BAD_REQUEST.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErroResponse> badCredentialsException(BadCredentialsException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.UNAUTHORIZED.value(), 
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
	}
	
	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<ErroResponse> authorizationDeniedException(AuthorizationDeniedException e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.FORBIDDEN.value(), 
				"Acesso negado, você não tem permissão para realizar essa operação",
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResponse> exception(Exception e,
			HttpServletRequest request) {
		ErroResponse erro = new ErroResponse(
				LocalDateTime.now(), 
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"Erro inesperado: " + e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
	
}
