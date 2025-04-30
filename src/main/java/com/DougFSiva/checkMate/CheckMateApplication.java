package com.DougFSiva.checkMate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.DougFSiva.checkMate.service.usuario.CriaUsuarioAdminDefault;

@SpringBootApplication
public class CheckMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckMateApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(CriaUsuarioAdminDefault criaUsuarioAdminDefault) {
	    return args -> {
	      criaUsuarioAdminDefault.criar();
	    };
	}

}
