package com.ambiental;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;

import com.puc.monitoramento.ejbMonitoramento.Monitoramento;
import com.puc.monitoramento.ejbMonitoramento.model.CondiSeguranca;
import com.puc.monitoramento.ejbMonitoramento.model.Sensor;

@ManagedBean
@SessionScoped
public class MonitoramentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	Monitoramento monitoramentoEjb = new Monitoramento() ;

	
	Sensor sensor = new Sensor();
	List<Sensor> sensores = null;
	CondiSeguranca condi = new CondiSeguranca();
	List<CondiSeguranca> condicoes = null;
	
	String msgTela="";
	
	
	public String home() {
		sensor = new Sensor();
		sensores = null;
		condi = new CondiSeguranca();
		condicoes = null;
		 msgTela="";
		 
		return "opcoes";
	}
	
	public String telaReiniciarDados() {
		monitoramentoEjb.inicializar();
		msgTela = "Dados do Monitoramento reiniciados";
		return "sucesso";
	}
	public String telaCadastrarSensor() {
		return "cadastrarsensor";
	}
	public String telaConsultarSensores() {
		try {
			sensores = monitoramentoEjb.listarSensores();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return "listarsensores";
		
	}
	public String telaAtualizarSensor() {
		return "atualizarsensor";
	}
	public String telaCadastrarCondi() {
		return "cadastrarcondi";
	}
	public String telaConsultarCondi() {
		try {
			condicoes = monitoramentoEjb.obterCondicoes();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return "listarcondicoes";
	}
	
	
	public String incluirSensor() {
		monitoramentoEjb.cadastrarSensor(sensor);
		msgTela = "Sensor Cadastrado com suceso";
		return "sucesso";
	}
	public String incluirCondicao() {
		monitoramentoEjb.cadastrarCondicoes(condi);
		msgTela = "Condição de segurança cadastrada com suceso";
		return "sucesso";
	}
	public String atualizarSensor() {
		monitoramentoEjb.atualizarSensor(sensor);
		msgTela = "Valor do sensor Atualizado com suceso";
		return "sucesso";
	}
	
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}
	public String getMsgTela() {
		return msgTela;
	}
	public void setMsgTela(String msgTela) {
		this.msgTela = msgTela;
	}
	public CondiSeguranca getCondi() {
		return condi;
	}
	public void setCondi(CondiSeguranca condi) {
		this.condi = condi;
	}
	public List<CondiSeguranca> getCondicoes() {
		return condicoes;
	}
	public void setCondicoes(List<CondiSeguranca> condicoes) {
		this.condicoes = condicoes;
	}

	
}