package com.puc.moitoramento.BFFMonitoramento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class BffMonitoramentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffMonitoramentoApplication.class, args);
	}
}
