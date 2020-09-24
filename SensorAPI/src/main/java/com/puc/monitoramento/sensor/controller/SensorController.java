package com.puc.monitoramento.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.monitoramento.sensor.dao.model.Sensor;
import com.puc.monitoramento.sensor.service.ISensorService;

@RestController
public class SensorController {

	@Autowired
	ISensorService monService;

	@RequestMapping(value = "/obterSensores", method = RequestMethod.GET)
	public List<Sensor> obterSensores() {
		return monService.listarSensores();
	}
	@RequestMapping(value = "/buscarSensor", method = RequestMethod.GET)
	public Sensor buscarSensor(@RequestParam int idSensor) {
		return monService.buscarSensor(idSensor);
	}
	@RequestMapping(value = "/cadastrarSensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrarSensor(@RequestBody Sensor sensor) {
		monService.cadastrarSensor(sensor);
	}
	@RequestMapping(value = "/atualizarSensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void atualizarSensor(@RequestBody Sensor sensor) {
		monService.atualizarSensor(sensor);
	}
	@RequestMapping(value = "/limparBase", method = RequestMethod.GET)
	public void limparBase() {
		monService.limparBase();
	}
}