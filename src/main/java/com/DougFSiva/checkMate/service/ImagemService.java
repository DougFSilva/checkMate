package com.DougFSiva.checkMate.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.model.Ambiente;
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
	
	public String salvarImagemDeAmbiente(MultipartFile imagem, Ambiente ambiente) {
		return ""; 
	}
	
	public void deletarImagemDeAmbiente(Ambiente ambiente) {
		
	}
	
	public String buscarImagemDefaultDeAmbiente() {
		return "";
	}
}
