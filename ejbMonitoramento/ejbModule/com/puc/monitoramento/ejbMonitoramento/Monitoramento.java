package com.puc.monitoramento.ejbMonitoramento;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.puc.monitoramento.ejbMonitoramento.model.Chamado;
import com.puc.monitoramento.ejbMonitoramento.model.CondiSeguranca;
import com.puc.monitoramento.ejbMonitoramento.model.Sensor;

public class Monitoramento {
	
	private String serverAddr = Util.getProperty("bff.server.address");
	private String serverPort = Util.getProperty("bff.server.port");
	

	public Sensor buscarSensor(int idSensor) {

		RestTemplate rest = new RestTemplate();
		Sensor sensor = rest.getForObject(serverAddr + serverPort + "/buscarSensor?idSensor=" + idSensor, Sensor.class);
		
		return sensor;
	}
	
	public List<Sensor> listarSensores() {

		RestTemplate rest = new RestTemplate();
		List<Sensor> sensores = rest.getForObject(serverAddr + serverPort + "/obterSensores", List.class);
		
		return sensores;
	}
	
	public String cadastrarSensor(Sensor sensor) {
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(sensor), headers);
			 ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/cadastrarSensor", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	public String atualizarSensor(Sensor sensor) {
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Gson gson = new Gson();

			HttpEntity<String> request = new HttpEntity<String>(gson.toJson(sensor), headers);
			ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/atualizarSensor",
					request, String.class);
			if (resposta.getStatusCode() == HttpStatus.OK) {
				return "Sucesso";
			}
			return "Erro no serviço";

		} catch (Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	
	public List<CondiSeguranca> obterCondicoes() {
		
		RestTemplate rest = new RestTemplate();
		List<CondiSeguranca> condicoes = rest.getForObject(serverAddr + serverPort + "/obterCondicoes", List.class);
		
		return condicoes;
	}
	
	public CondiSeguranca buscarCondicao(int idCondi) {

		RestTemplate rest = new RestTemplate();
		CondiSeguranca condicao = rest.getForObject(serverAddr + serverPort + "/buscarCondicao?idCondi=" + idCondi, CondiSeguranca.class);
		
		return condicao;
	}
	

	public String cadastrarCondicoes(CondiSeguranca condi) {

		try {

			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Gson gson = new Gson();

			HttpEntity<String> request = new HttpEntity<String>(gson.toJson(condi), headers);
			ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/cadastrarCondicoes",
					request, String.class);
			if (resposta.getStatusCode() == HttpStatus.OK) {
				return "Sucesso";
			}
			return "Erro no serviço";

		} catch (Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	public String chamadoEmergenciaDC(Chamado chamado) {
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(chamado), headers);
			 ResponseEntity<String> resposta = a.postForEntity(serverAddr + serverPort + "/chamadoEmergenciaDC", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	public String inicializar() {
		
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(serverAddr + serverPort + "/inicializar", String.class);
		
		return resp;
	}
		
}
