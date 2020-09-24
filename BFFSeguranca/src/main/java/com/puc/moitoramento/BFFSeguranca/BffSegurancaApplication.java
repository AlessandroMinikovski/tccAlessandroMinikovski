package com.puc.moitoramento.BFFSeguranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class BffSegurancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffSegurancaApplication.class, args);
	}
}
