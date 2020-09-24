package com.ambiental;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;

import com.puc.seguranca.ejbMonitoramento.Seguranca;
import com.puc.seguranca.ejbMonitoramento.model.Alerta;
import com.puc.seguranca.ejbMonitoramento.model.Verificacao;

@ManagedBean
@SessionScoped
public class SegurancaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	Seguranca segurancaoEjb = new Seguranca() ;
	
	Alerta alerta = new Alerta();
	List<Alerta> alertas = null;
	Verificacao verificacao = new Verificacao();
	List<Verificacao> verificacoes = null;
	String msgTela = "";
	
	public String home() {
		
		alerta = new Alerta();
		alertas = null;
		verificacao = new Verificacao();
		verificacoes = null;
		msgTela = "";
		 
		return "opcoes";
	}
	
	public String telaReiniciarDados() {
		segurancaoEjb.inicializar();
		msgTela = "Dados da Segurança e Monitoramento reiniciados";
		return "sucesso";
	}

	public String telaCadastrarAlerta() {
		return "cadastraralerta";
	}

	public String telaConsultarAlertas() {
		try {
			alertas = segurancaoEjb.obterAlertas();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return "listaralertas";
	}

	public String telaCadastrarVeri() {
		return "cadastrarverificacao";
	}

	public String telaConsultarVeri() {
		try {
			verificacoes = segurancaoEjb.obterVerificacoes();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return "listarverificacoes";
	}
	
	
	
	public String incluirAlerta() {
		segurancaoEjb.cadastrarAlerta(alerta);
		msgTela = "Alerta cadastrada com suceso";
		return "sucesso";
	}
	
	public String incluirVerificacao() {
		segurancaoEjb.cadastrarVerificacao(verificacao);
		msgTela = "Verificação cadastrada com suceso";
		return "sucesso";
	}
	
	public void ativarVerificacao(ActionEvent event) {
		int idVerificacao = (int) event.getComponent().getAttributes().get("idVerificacao");
		segurancaoEjb.ativarVerificacao(idVerificacao);
	}
	public void desativarVerificacao(ActionEvent event) {
		int idVerificacao = (int) event.getComponent().getAttributes().get("idVerificacao");
		segurancaoEjb.desativarVerificacao(idVerificacao);
	}


	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}

	public Verificacao getVerificacao() {
		return verificacao;
	}

	public void setVerificacao(Verificacao verificacao) {
		this.verificacao = verificacao;
	}

	public List<Verificacao> getVerificacoes() {
		return verificacoes;
	}

	public void setVerificacoes(List<Verificacao> verificacoes) {
		this.verificacoes = verificacoes;
	}

	public String getMsgTela() {
		return msgTela;
	}

	public void setMsgTela(String msgTela) {
		this.msgTela = msgTela;
	}
	
	
}