package com.puc.seguranca.verificacao.service;

import java.util.List;

import com.puc.seguranca.verificacao.dao.model.Verificacao;

public interface IVerificacaoService {
	
	List<Verificacao>obterVerificacoes();
	Verificacao buscarVerificacao(int idVerificacao);
	void cadastrarVerificacao(Verificacao verificacao);
	
	String ativarVerificacao(int idVerificacao);
	String desativarVerificacao(int idVerificacao);
	
	void limparBase();

}
