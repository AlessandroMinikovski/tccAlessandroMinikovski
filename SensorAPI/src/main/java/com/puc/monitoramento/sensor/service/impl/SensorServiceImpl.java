package com.puc.monitoramento.sensor.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.monitoramento.sensor.dao.ISensorDao;
import com.puc.monitoramento.sensor.dao.model.Sensor;
import com.puc.monitoramento.sensor.service.ISensorService;

@Service
public class SensorServiceImpl implements ISensorService {

	
	@Autowired
	ISensorDao sensorDao;

	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

	public void logar(String txt) {
		System.out.println("[SensorAPI]" + formater.format(new Date()) + " - " + txt);
	}
	
	@Override
	public List<Sensor> listarSensores() {
		logar("Obtendo sensores");
		return  sensorDao.obterSensores();
				 
	}
	@Override
	public Sensor buscarSensor(int idSensor) {
		logar("Buscando sensor id = " + idSensor);
		return  sensorDao.buscarSensor(idSensor);
	}

	@Override
	public void cadastrarSensor(Sensor sensor) {
		logar("Cadastrando sensor modelo = " +sensor.getModelo());
		sensorDao.cadastrarSensor(sensor);
	}
	
	@Override
	public void atualizarSensor(Sensor sensor) {
		logar("Atualizando sensor id = " + sensor.getId());
		sensorDao.atualizarSensor(sensor);
	}
	
	@Override
	public void limparBase() {
		logar("Limpando base");
		sensorDao.limparBase();
	}



}