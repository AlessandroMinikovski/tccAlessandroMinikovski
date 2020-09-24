package com.puc.seguranca.alerta.service;

import java.util.List;

import com.puc.seguranca.alerta.dao.model.Alerta;

public interface IAlertaService {
	
	List<Alerta>obterAlertas();
	Alerta buscarAlerta(int idAlerta);
	void cadastrarAlerta(Alerta alerta);
	
	String ativarAlerta(int idAlerta) ;
	String desativarAlerta(int idAlerta);
	
	void limparBase();

}