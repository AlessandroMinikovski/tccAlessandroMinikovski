package com.puc.monitoramento.sensor.dao;

import java.util.List;

import com.puc.monitoramento.sensor.dao.model.Sensor;

public interface ISensorDao {
	
	List<Sensor>obterSensores();
	Sensor buscarSensor(int idSensor);
	void cadastrarSensor(Sensor sensor);
	void atualizarSensor(Sensor sensor);
	void limparBase();
	
}
