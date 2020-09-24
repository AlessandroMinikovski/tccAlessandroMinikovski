package com.puc.seguranca.verificacao.dao;

import java.util.List;

import com.puc.seguranca.verificacao.dao.model.Verificacao;

public interface IVerificacaoDao {
	
	List<Verificacao>obterVerificacoes();
	Verificacao buscarVerificacao(int idVerificacao);
	void cadastrarVerificacao(Verificacao verificacao);
	
	boolean ativarVerificacao(int idVerificacao);
	boolean desativarVerificacao(int idVerificacao);
	
	void limparBase();

}
