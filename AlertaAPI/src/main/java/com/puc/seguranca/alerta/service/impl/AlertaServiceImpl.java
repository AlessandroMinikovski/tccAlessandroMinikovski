package com.puc.seguranca.alerta.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.seguranca.alerta.dao.IAlertaDao;
import com.puc.seguranca.alerta.dao.model.Alerta;
import com.puc.seguranca.alerta.service.IAlertaService;

@Service
public class AlertaServiceImpl implements IAlertaService {

	
	@Autowired
	IAlertaDao alertaDao;
	
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
	
	public void logar(String txt) {
		System.out.println("[AlertaAPI]" + formater.format(new Date()) + " - " + txt);
	}

	@Override
	public List<Alerta> obterAlertas() {
		
		logar("Obtendo todas a alertas");
		return  alertaDao.obterAlertas();
				 
	}
	@Override
	public Alerta buscarAlerta(int idAlerta) {
		logar("Buscando alerta id = " + idAlerta);
		return  alertaDao.buscarAlerta(idAlerta);
	}



	@Override
	public void cadastrarAlerta(Alerta alerta) {
		logar("Cadastrando alerta. Modelo = " + alerta.getModelo());
		alertaDao.cadastrarAlerta(alerta);
	}
	@Override
	public String ativarAlerta(int idAlerta) {
		logar("Ativando alerta id = " + idAlerta);
		Alerta alerta = this.buscarAlerta(idAlerta);
		if(alerta == null) {
			String txt = "Alerta não encontrada";
			logar(txt);
			return txt;
		}
		if(alerta.getAcionado().equalsIgnoreCase("V")) {
			String txt = "Alerta já está acionada";
			logar(txt);
			return txt;
		}
		if (alertaDao.ativarAlerta(idAlerta)) {
			return "Sucesso";
		}
		String txt = "Erro durante a ativação da alerta";
		logar(txt);
		return txt;
	}
	
	@Override
	public String desativarAlerta(int idAlerta) {
		logar("Desativando alerta id = " + idAlerta);
		Alerta alerta = this.buscarAlerta(idAlerta);
		if(alerta == null) {
			String txt = "Alerta não encontrada";
			logar(txt);
			return txt;
		}
		if(!alerta.getAcionado().equalsIgnoreCase("V")) {
			String txt = "Alerta já está desativada";
			logar(txt);
			return txt;
		}
		if (alertaDao.desativarAlerta(idAlerta)) {
			return "Sucesso";
		}
		String txt = "Erro durante a desativação da alerta";
		logar(txt);
		return txt;
	}
	@Override
	public void limparBase() {
		logar("Limpando a base");
		alertaDao.limparBase();
		
	}



}