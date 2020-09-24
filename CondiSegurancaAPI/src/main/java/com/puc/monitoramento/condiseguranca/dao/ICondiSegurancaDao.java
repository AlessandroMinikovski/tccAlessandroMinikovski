package com.puc.monitoramento.condiseguranca.dao;

import java.util.List;

import com.puc.monitoramento.condiseguranca.dao.model.CondiSeguranca;

public interface ICondiSegurancaDao {
	
	List<CondiSeguranca>obterCondicoes();
	CondiSeguranca buscarCondicao(int idCondi);
	void cadastrarCondicoes(CondiSeguranca condi);
	void limparBase();
}
