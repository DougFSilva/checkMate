package com.DougFSiva.checkMate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.DougFSiva.checkMate.service.usuario.CriaUsuarioAdminDefault;

@SpringBootApplication
@EnableScheduling
@EnableCaching
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
