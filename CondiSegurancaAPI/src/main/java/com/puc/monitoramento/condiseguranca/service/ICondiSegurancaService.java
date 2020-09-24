package com.puc.monitoramento.condiseguranca.service;

import java.util.List;

import com.puc.monitoramento.condiseguranca.dao.model.CondiSeguranca;

public interface ICondiSegurancaService {
	
	List<CondiSeguranca> obterCondicoes();
	CondiSeguranca buscarCondicao(int idCondi);
	void cadastrarCondicoes(CondiSeguranca sensor);
	void limparBase();
}