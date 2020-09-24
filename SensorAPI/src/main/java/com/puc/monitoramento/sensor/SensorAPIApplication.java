package com.puc.monitoramento.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SensorAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorAPIApplication.class, args);
	}

}
