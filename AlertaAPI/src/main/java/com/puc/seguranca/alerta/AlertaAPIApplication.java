package com.puc.seguranca.alerta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class AlertaAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertaAPIApplication.class, args);
	}

}
