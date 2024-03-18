package com.application.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReporteapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReporteapiApplication.class, args);
		System.out.println("Api de Reportes Corriendo");
	}

}
