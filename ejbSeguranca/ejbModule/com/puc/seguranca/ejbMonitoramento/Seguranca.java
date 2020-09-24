package com.puc.seguranca.ejbMonitoramento;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.puc.seguranca.ejbMonitoramento.model.Alerta;
import com.puc.seguranca.ejbMonitoramento.model.Verificacao;

public class Seguranca {
	
	private String serverAddr = Util.getProperty("bff.server.address");
	private String serverPort = Util.getProperty("bff.server.port");
	
	
	
	public List<Alerta> obterAlertas() {

		RestTemplate rest = new RestTemplate();
		List<Alerta> alertaes = rest.getForObject(serverAddr + serverPort + "/obterAlertas", List.class);
		
		return alertaes;
	}
	
	

	public Alerta buscarAlerta(int idAlerta) {

		RestTemplate rest = new RestTemplate();
		Alerta alerta = rest.getForObject(serverAddr + serverPort + "/buscarAlerta?idAlerta=" + idAlerta, Alerta.class);
		
		return alerta;
	}
	

	
	public String cadastrarAlerta(Alerta alerta) {
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(alerta), headers);
			 ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/cadastrarAlerta", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	


	public String ativarAlerta(int idAlerta) {
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/ativarAlerta?idAlerta=" + idAlerta, String.class);
		return resp;
	}



	public String desativarAlerta(int idAlerta) {
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/desativarAlerta?idAlerta=" + idAlerta, String.class);
		return resp;
	}		

	
	public List<Verificacao>obterVerificacoes(){

		RestTemplate rest = new RestTemplate();
		List<Verificacao> verificacoes = rest.getForObject(serverAddr + serverPort + "/obterVerificacoes", List.class);
		
		return verificacoes;
	}
	
	

	public Verificacao buscarVerificacao(int idVerificacao) {

		RestTemplate rest = new RestTemplate();
		Verificacao verificacao = rest.getForObject(serverAddr + serverPort + "/buscarVerificacao?idVerificacao=" + idVerificacao, Verificacao.class);
		
		return verificacao;
	}
	

	
	public String cadastrarVerificacao(Verificacao verificacao) {
		try {
		
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(verificacao), headers);
			 ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/cadastrarVerificacao", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	
	
	public String ativarVerificacao(int idVerificacao) {

		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/ativarVerificacao?idVerificacao=" + idVerificacao, String.class);
		
		return resp;
	}
	
	public String desativarVerificacao(int idVerificacao) {

		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/desativarVerificacao?idVerificacao=" + idVerificacao, String.class);
		
		return resp;
	}



	public String inicializar() {
		
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/inicializar", String.class);
		
		return resp;
	}
		
}
