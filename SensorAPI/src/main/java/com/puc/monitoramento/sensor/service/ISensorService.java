package com.puc.monitoramento.sensor.service;

import java.util.List;

import com.puc.monitoramento.sensor.dao.model.Sensor;

public interface ISensorService {
	
	List<Sensor> listarSensores();
	Sensor buscarSensor(int idSensor);
	void cadastrarSensor(Sensor sensor);
	void atualizarSensor(Sensor sensor);
	void limparBase();
}