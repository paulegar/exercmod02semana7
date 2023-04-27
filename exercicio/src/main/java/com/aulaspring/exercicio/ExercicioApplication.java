package com.aulaspring.exercicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExercicioApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(ExercicioApplication.class);

	public static void main(String[] args) {
		logger.info("iniciando servidor");
		SpringApplication.run(ExercicioApplication.class, args);
		logger.info("servidor iniciado");
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
