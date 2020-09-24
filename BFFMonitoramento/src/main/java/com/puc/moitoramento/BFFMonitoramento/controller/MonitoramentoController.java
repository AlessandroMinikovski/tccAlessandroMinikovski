package com.puc.moitoramento.BFFMonitoramento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.moitoramento.BFFMonitoramento.model.Chamado;
import com.puc.moitoramento.BFFMonitoramento.model.CondiSeguranca;
import com.puc.moitoramento.BFFMonitoramento.model.Sensor;
import com.puc.moitoramento.BFFMonitoramento.service.IMonitoramentoService;

@RestController
public class MonitoramentoController {

	@Autowired
	IMonitoramentoService monService;
	
	@RequestMapping(value = "/inicializar", method = RequestMethod.GET)
	public String inicializar() {
		return monService.inicializar();
	}

	@RequestMapping(value = "/obterSensores", method = RequestMethod.GET)
	public List<Sensor> obterSensores() {
		return monService.listarSensores();
	}
	
	@RequestMapping(value = "/buscarSensor", method = RequestMethod.GET)
	public Sensor buscarSensor(@RequestParam int idSensor) {
		return monService.buscarSensor(idSensor);
	}


	@RequestMapping(value = "/cadastrarSensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cadastrarSensor(@RequestBody Sensor sensor) {
		return monService.cadastrarSensor(sensor);
	}
	
	@RequestMapping(value = "/atualizarSensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String atualizarSensor(@RequestBody Sensor sensor) {
		return monService.atualizarSensor(sensor);
	}
	

	@RequestMapping(value = "/obterCondicoes", method = RequestMethod.GET)
	public List<CondiSeguranca> obterCondicoes() {
		return monService.obterCondicoes();
	}
	
	@RequestMapping(value = "/buscarCondicao", method = RequestMethod.GET)
	public CondiSeguranca buscarCondicao(@RequestParam int idCondi) {
		return monService.buscarCondicao(idCondi);
	}

	
	@RequestMapping(value = "/cadastrarCondicoes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cadastrarCondicoes(@RequestBody CondiSeguranca condi) {
		return monService.cadastrarCondicoes(condi);
	}
	
	@RequestMapping(value = "/chamadoEmergenciaDC", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String chamadoEmergenciaDC(@RequestBody Chamado chamado) {
		return monService.chamadoEmergenciaDC(chamado);
	}


}