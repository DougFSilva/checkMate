package com.DougFSiva.checkMate.service.tarefas;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeletaUsuariosInativosTask {

	private final static LoggerPadrao logger = new LoggerPadrao(DeletaUsuariosInativosTask.class);
	 private final UsuarioRepository usuarioRepository;

	    // Executa todo dia 1º às 00:00
	    @Scheduled(cron = "0 0 0 1 * ?")
	    public void removerUsuariosComDataExpirada() {
	        List<Usuario> usuarios = usuarioRepository.findByDataValidadeBefore(LocalDate.now());
	        usuarioRepository.deleteAll(usuarios);
	        logger.info("Usuários expirados removidos: " + usuarios.size());
	    }
}
