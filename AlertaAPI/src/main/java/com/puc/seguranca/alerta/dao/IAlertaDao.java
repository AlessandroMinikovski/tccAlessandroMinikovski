package com.puc.seguranca.alerta.dao;

import java.util.List;

import com.puc.seguranca.alerta.dao.model.Alerta;

public interface IAlertaDao {
	
	List<Alerta>obterAlertas();
	Alerta buscarAlerta(int idAlerta);
	void cadastrarAlerta(Alerta alerta);
	
	boolean ativarAlerta(int idAlerta) ;
	boolean desativarAlerta(int idAlerta);
	
	void limparBase();

}
