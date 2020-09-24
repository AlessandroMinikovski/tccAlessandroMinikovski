package com.puc.monitoramento.condiseguranca.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.monitoramento.condiseguranca.dao.ICondiSegurancaDao;
import com.puc.monitoramento.condiseguranca.dao.model.CondiSeguranca;
import com.puc.monitoramento.condiseguranca.service.ICondiSegurancaService;

@Service
public class CondiSegurancaServiceImpl implements ICondiSegurancaService {

	@Autowired
	ICondiSegurancaDao sensorDao;

	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

	public void logar(String txt) {
		System.out.println("[CondiSegurancaAPI]" + formater.format(new Date()) + " - " + txt);
	}

	@Override
	public List<CondiSeguranca> obterCondicoes() {
		logar("Obtendo condições");
		return sensorDao.obterCondicoes();

	}

	@Override
	public CondiSeguranca buscarCondicao(int idCondi) {
		logar("Buscanco condição id = " + idCondi);
		return sensorDao.buscarCondicao(idCondi);
	}

	@Override
	public void cadastrarCondicoes(CondiSeguranca cond) {
		logar("Cadastrando condição nome = " + cond.getNomeCondi());
		sensorDao.cadastrarCondicoes(cond);
	}

	@Override
	public void limparBase() {
		logar("Limpando base");
		sensorDao.limparBase();
	}

}