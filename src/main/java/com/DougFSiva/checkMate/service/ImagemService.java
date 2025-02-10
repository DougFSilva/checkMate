package com.DougFSiva.checkMate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.model.Usuario;

@Service
public class ImagemService {
	
	public String salvarFotoDeUsuario(MultipartFile foto, Usuario usuario) {
		return "";
	}
	
	public void deletarFotoDeUsuario(Usuario usuario) {
		
	}

	public String buscarFotoDefaultDeUsuario() {
		return "";
	}
}
