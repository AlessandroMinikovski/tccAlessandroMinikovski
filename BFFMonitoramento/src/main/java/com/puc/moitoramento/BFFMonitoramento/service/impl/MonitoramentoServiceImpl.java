package com.puc.moitoramento.BFFMonitoramento.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.puc.moitoramento.BFFMonitoramento.model.Chamado;
import com.puc.moitoramento.BFFMonitoramento.model.CondiSeguranca;
import com.puc.moitoramento.BFFMonitoramento.model.Sensor;
import com.puc.moitoramento.BFFMonitoramento.service.IMonitoramentoService;

@Service
public class MonitoramentoServiceImpl implements IMonitoramentoService {

	@Value("${sensor.server.address}")
	private String sensorServerAddress;
	
	@Value("${sensor.micro.port}")
	private String sensorPort;
	
	@Value("${condi.server.address}")
	private String condiServerAddress;
	
	@Value("${condi.micro.port}")
	private String condiPort;
	
	@Value("${chamado.server.address}")
	private String chamadoServerAddress;
	
	@Value("${chamado.micro.port}")
	private String chamadoPort;
	
	
	
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

	public void logar(String txt) {
		System.out.println("[BFFMonitoramento]" + formater.format(new Date()) + " - " + txt);
	}

	@Override
	public Sensor buscarSensor(int idSensor) {
		//logar("Buscando o sendor id = " + idSensor);

		RestTemplate rest = new RestTemplate();
		Sensor sensor = rest.getForObject(sensorServerAddress + sensorPort + "/buscarSensor?idSensor=" + idSensor, Sensor.class);
		
		return sensor;
	}
	
	@Override
	public List<Sensor> listarSensores() {
		logar("Listando os sensores");
		RestTemplate rest = new RestTemplate();
		List<Sensor> sensores = rest.getForObject(sensorServerAddress + sensorPort + "/obterSensores", List.class);
		
		return sensores;
	}
	
	@Override
	public String cadastrarSensor(Sensor sensor) {
		logar("Cadastrando sensor modelo = " + sensor.getModelo());
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(sensor), headers);
			 ResponseEntity<String> resposta = a.postForEntity(sensorServerAddress + sensorPort + "/cadastrarSensor", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	@Override
	public String atualizarSensor(Sensor sensor) {
		logar("Atualizando sensor id = " + sensor.getId());
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Gson gson = new Gson();

			HttpEntity<String> request = new HttpEntity<String>(gson.toJson(sensor), headers);
			ResponseEntity<String> resposta = a.postForEntity(sensorServerAddress + sensorPort + "/atualizarSensor",
					request, String.class);
			if (resposta.getStatusCode() == HttpStatus.OK) {
				return "Sucesso";
			}
			return "Erro no serviço";

		} catch (Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	
	@Override
	public List<CondiSeguranca> obterCondicoes() {
		logar("Obtendo condições");
		RestTemplate rest = new RestTemplate();
		List<CondiSeguranca> condicoes = rest.getForObject(condiServerAddress + condiPort + "/obterCondicoes", List.class);
		
		return condicoes;
	}
	
	@Override
	public CondiSeguranca buscarCondicao(int idCondi) {
		//logar("Buscando condição id = " + idCondi);
		RestTemplate rest = new RestTemplate();
		CondiSeguranca condicao = rest.getForObject(condiServerAddress + condiPort + "/buscarCondicao?idCondi=" + idCondi, CondiSeguranca.class);
		
		return condicao;
	}
	

	@Override
	public String cadastrarCondicoes(CondiSeguranca condi) {
		logar("Cadastrando condição nome = " + condi.getNomeCondi());
		try {

			Sensor sensor = this.buscarSensor(condi.getIdSensor());

			//if (sensor == null) {
			//	return "Erro: Sensor não encontrado";
			//}

			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			Gson gson = new Gson();

			HttpEntity<String> request = new HttpEntity<String>(gson.toJson(condi), headers);
			ResponseEntity<String> resposta = a.postForEntity(condiServerAddress + condiPort + "/cadastrarCondicoes",
					request, String.class);
			if (resposta.getStatusCode() == HttpStatus.OK) {
				return "Sucesso";
			}
			return "Erro no serviço";

		} catch (Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	@Override
	public String chamadoEmergenciaDC(Chamado chamado) {
		logar("Chamando Defesa Civil nome = " + chamado.getNome());
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(chamado), headers);
			 ResponseEntity<String> resposta = a.postForEntity(chamadoServerAddress + chamadoPort + "/chamadoEmergenciaDC", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return resposta.getBody();
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}

	@Override
	public String inicializar() {
		
		logar("Dando restart nos dados");
		//limpa os dados
		RestTemplate rest = new RestTemplate();
		
		
		rest.getForObject(sensorServerAddress + sensorPort + "/limparBase", String.class);
		rest = new RestTemplate();
		rest.getForObject(condiServerAddress + condiPort + "/limparBase", String.class);
		
		//cria os sensores
		Sensor sensor = new Sensor();
		sensor.setModelo("Modelo tipo 1");
		sensor.setFinalidade("Mapear pressão da agua na barragem 1");
		sensor.setTipoConexao("Coaxial");
		sensor.setValor(5);
		this.cadastrarSensor(sensor);
		
		sensor = new Sensor();
		sensor.setModelo("Modelo tipo 2");
		sensor.setFinalidade("Mapear temperatura da agua na barragem 1");
		sensor.setTipoConexao("Coaxial");
		sensor.setValor(15);
		this.cadastrarSensor(sensor);
		
		/*sensor = new Sensor();
		sensor.setModelo("Modelo tipo 1");
		sensor.setFinalidade("Mapear pressão da agua na barragem 2");
		sensor.setTipoConexao("Coaxial");
		sensor.setValor(10);
		this.cadastrarSensor(sensor);
		
		sensor = new Sensor();
		sensor.setModelo("Modelo tipo 2");
		sensor.setFinalidade("Mapear temperatura da agua na barragem 2");
		sensor.setTipoConexao("Coaxial");
		sensor.setValor(8);
		this.cadastrarSensor(sensor);*/
		
		
		
		CondiSeguranca condi = new CondiSeguranca();
		
		condi.setIdSensor(1);
		condi.setNomeCondi("Pressão da agua baixo perigo");
		condi.setTipoAcionamento(2);
		condi.setValorMaximoSensor(20);
		condi.setValorMinimoSensor(-9999);
		this.cadastrarCondicoes(condi);
		
		condi = new CondiSeguranca();
		condi.setIdSensor(2);
		condi.setNomeCondi("Temperatura da água baixo perigo");
		condi.setTipoAcionamento(2);
		condi.setValorMaximoSensor(25);
		condi.setValorMinimoSensor(5);
		this.cadastrarCondicoes(condi);
		
		condi = new CondiSeguranca();
		condi.setIdSensor(1);
		condi.setNomeCondi("Pressão da agua alto perigo");
		condi.setTipoAcionamento(1);
		condi.setValorMaximoSensor(30);
		condi.setValorMinimoSensor(-9999);
		this.cadastrarCondicoes(condi);
		
		condi = new CondiSeguranca();
		condi.setIdSensor(2);
		condi.setNomeCondi("Temperatura da água alto perigo");
		condi.setTipoAcionamento(1);
		condi.setValorMaximoSensor(30);
		condi.setValorMinimoSensor(3);
		this.cadastrarCondicoes(condi);
				
		logar("Restart com sucesso");
		return "sucesso";
	}
}