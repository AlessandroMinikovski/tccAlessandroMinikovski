package com.puc.monitoramento.defesacivil.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.puc.monitoramento.defesacivil.model.Chamado;

@RestController
public class DefesaCivilController {


	@RequestMapping(value = "/chamadoEmergenciaDC", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String chamadoEmergenciaDC(@RequestBody Chamado sensor) {
		System.out.println("Defesa civil sendo chamada. Nome = " + sensor.getNome());
		return "Registro recebido pela Defesa Civil. Entraremos em contato o quanto antes.";
	}

}