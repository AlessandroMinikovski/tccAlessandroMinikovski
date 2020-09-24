package com.puc.moitoramento.BFFMonitoramento.service;

import java.util.List;

import com.puc.moitoramento.BFFMonitoramento.model.Chamado;
import com.puc.moitoramento.BFFMonitoramento.model.CondiSeguranca;
import com.puc.moitoramento.BFFMonitoramento.model.Sensor;

public interface IMonitoramentoService {
	
	List<Sensor> listarSensores();
	Sensor buscarSensor(int idSensor);
	String cadastrarSensor(Sensor sensor);
	String atualizarSensor(Sensor sensor) ;
	
	List<CondiSeguranca> obterCondicoes();
	CondiSeguranca buscarCondicao(int idCondi);
	String cadastrarCondicoes(CondiSeguranca sensor);
	
	String chamadoEmergenciaDC(Chamado chamado);
	
	String inicializar();
	
	
	
}