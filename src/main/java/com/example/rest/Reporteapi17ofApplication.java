package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Reporteapi17ofApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(Reporteapi17ofApplication.class, args);
		System.out.println("Api de Reportes Corriendo");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Reporteapi17ofApplication.class);
	}

}
