package com.DougFSiva.checkMate.service.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.EditaUsuarioForm;
import com.DougFSiva.checkMate.dto.resposta.UsuarioResposta;
import com.DougFSiva.checkMate.model.Perfil;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class EditaUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(EditaUsuarioService.class);
	private final UsuarioRepository repository;
	private final ImagemService imagemService;

	public EditaUsuarioService(UsuarioRepository repository, ImagemService imagemService) {
		this.repository = repository;
		this.imagemService = imagemService;
	}
	
	public UsuarioResposta editar(EditaUsuarioForm form) {
		validarUsuarioAutenticado(form.ID());
		Usuario usuario = repository.findByIdOrElseThrow(form.ID());
		Usuario usuarioAtualizado = atualizarDadosDoUsuario(usuario, form);
		Usuario usuarioSalvo = repository.save(usuarioAtualizado);
		logger.infoComUsuario(String.format("Usuário com ID %d editado!", form.ID()));
		return new UsuarioResposta(usuarioSalvo);
	}
	
	private Usuario atualizarDadosDoUsuario(Usuario usuario, EditaUsuarioForm form) {
		usuario.setNome(form.nome());
		usuario.setCPF(form.CPF());
		usuario.setEmail(form.email());
		usuario.setPerfil(new Perfil(form.tipoPerfil()));
		usuario.setDataValidade(form.dataValidade());
		String foto = form.foto() != null 
				? imagemService.salvarFotoDeUsuario(form.foto(), usuario) 
				: imagemService.buscarFotoDefaultDeUsuario();
		usuario.setFoto(foto);
		return usuario;
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
