package com.puc.seguranca.verificacao.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.seguranca.verificacao.dao.IVerificacaoDao;
import com.puc.seguranca.verificacao.dao.model.Verificacao;
import com.puc.seguranca.verificacao.service.IVerificacaoService;

@Service
public class VerificacaoServiceImpl implements IVerificacaoService {

	
	@Autowired
	IVerificacaoDao verificaDao;
	
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

	public void logar(String txt) {
		System.out.println("[VerificacaoAPI]" + formater.format(new Date()) + " - " + txt);
	}

	@Override
	public List<Verificacao> obterVerificacoes() {
		logar("Obtendo verificações");
		return verificaDao.obterVerificacoes();
				 
	}
	@Override
	public Verificacao buscarVerificacao(int idVerificacao) {
		logar("Buscando verificação id = " + idVerificacao);
		return verificaDao.buscarVerificacao(idVerificacao);
	}



	@Override
	public void cadastrarVerificacao(Verificacao verificacao) {
		logar("Cadastrando verificação frequencia = "+ verificacao.getFrequencia());
		verificaDao.cadastrarVerificacao(verificacao);
	}
	@Override
	public String ativarVerificacao(int idVerificacao) {
		logar("Ativando verificação id = " + idVerificacao);
		Verificacao verificacao = this.buscarVerificacao(idVerificacao);
		if(verificacao == null) {
			String txt = "Verificação não encontrada";
			logar(txt);
			return txt;
		}
		if(verificacao.getAcionado().equalsIgnoreCase("V")) {
			String txt = "Verificação já está rodando";
			logar(txt);
			return txt;
		}
		if (verificaDao.ativarVerificacao(idVerificacao)) {
			String txt = "Sucesso";
			logar(txt);
			return txt;
		}
		String txt = "Erro durante a parada da verificação";
		logar(txt);
		return txt;
	}
	@Override
	public String desativarVerificacao(int idVerificacao) {
		logar("Desativando verificação id = " + idVerificacao);
		Verificacao verificacao = this.buscarVerificacao(idVerificacao);
		if(verificacao == null) {
			String txt = "Verificação não encontrada";
			logar(txt);
			return txt;
		}
		if(!verificacao.getAcionado().equalsIgnoreCase("V")) {
			String txt = "Verificação já está parada";
			logar(txt);
			return txt;
		}
		if(verificaDao.desativarVerificacao(idVerificacao)){
			String txt = "Sucesso";
			logar(txt);
			return txt;
		}
		String txt = "Erro durante a parada da verificação";
		logar(txt);
		return txt;
		
	}
	@Override
	public void limparBase() {
		logar("Limpando a base");
		verificaDao.limparBase();
		
	}



}