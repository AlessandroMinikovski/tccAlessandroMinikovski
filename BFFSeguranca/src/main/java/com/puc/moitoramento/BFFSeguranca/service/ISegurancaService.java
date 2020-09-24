package com.puc.moitoramento.BFFSeguranca.service;

import java.util.List;

import com.puc.moitoramento.BFFSeguranca.model.Alerta;
import com.puc.moitoramento.BFFSeguranca.model.Chamado;
import com.puc.moitoramento.BFFSeguranca.model.CondiSeguranca;
import com.puc.moitoramento.BFFSeguranca.model.Sensor;
import com.puc.moitoramento.BFFSeguranca.model.Verificacao;

public interface ISegurancaService {
	
	List<Alerta>obterAlertas();
	Alerta buscarAlerta(int idAlerta);
	String cadastrarAlerta(Alerta alerta);
	
	String ativarAlerta(int idAlerta) ;
	String desativarAlerta(int idAlerta);
	
	
	List<Verificacao>obterVerificacoes();
	Verificacao buscarVerificacao(int idVerificacao);
	String cadastrarVerificacao(Verificacao verificacao);
	
	Sensor buscarSensor(int idSensor);
	CondiSeguranca buscarCondicao(int idCondi);
	
	String ativarVerificacao(int idVerificacao);
	String desativarVerificacao(int idVerificacao);
	
	String chamadoEmergenciaDC(Chamado chamado);
	
	String inicializar();
	
}