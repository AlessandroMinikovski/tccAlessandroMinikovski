package com.puc.moitoramento.BFFSeguranca.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.puc.moitoramento.BFFSeguranca.model.Alerta;
import com.puc.moitoramento.BFFSeguranca.model.Chamado;
import com.puc.moitoramento.BFFSeguranca.model.CondiSeguranca;
import com.puc.moitoramento.BFFSeguranca.model.Sensor;
import com.puc.moitoramento.BFFSeguranca.model.Verificacao;
import com.puc.moitoramento.BFFSeguranca.service.ISegurancaService;

@Service
public class SegurancaServiceImpl implements ISegurancaService {

	@Value("${alerta.server.address}")
	private String alertaServerAddress;
	
	@Value("${alerta.micro.port}")
	private String alertaPort;
	
	@Value("${veri.server.address}")
	private String veriServerAddress;
	
	@Value("${veri.micro.port}")
	private String veriPort;
	
	@Value("${bffMoni.server.address}")
	private String bffMoniServerAddress;
	
	@Value("${bffMoni.micro.port}")
	private String bffMoniPort;
	

	
	SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");

	public void logar(String txt) {
		System.out.println("[BFFSeguranca]" + formater.format(new Date()) + " - " + txt);
	}
	
	@Override
	public List<Alerta> obterAlertas() {
		logar("Obtendo alertas");
		RestTemplate rest = new RestTemplate();
		List<Alerta> alertaes = rest.getForObject(alertaServerAddress + alertaPort + "/obterAlertas", List.class);
		
		return alertaes;
	}
	
	

	@Override
	public Alerta buscarAlerta(int idAlerta) {
		//logar("Buscando alerta id = " + idAlerta);
		RestTemplate rest = new RestTemplate();
		Alerta alerta = rest.getForObject(alertaServerAddress + alertaPort + "/buscarAlerta?idAlerta=" + idAlerta, Alerta.class);
		
		return alerta;
	}
	

	
	@Override
	public String cadastrarAlerta(Alerta alerta) {
		logar("Cadastrando alerta modelo = " + alerta.getModelo());
		try {
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(alerta), headers);
			 ResponseEntity<String> resposta = a.postForEntity(alertaServerAddress + alertaPort + "/cadastrarAlerta", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	


	@Override
	public String ativarAlerta(int idAlerta) {
		//logar("Ativando alerta id = " + idAlerta);
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(alertaServerAddress + alertaPort + "/ativarAlerta?idAlerta=" + idAlerta, String.class);
		return resp;
	}



	@Override
	public String desativarAlerta(int idAlerta) {
		//logar("Desativando alerta id = " + idAlerta);
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(alertaServerAddress + alertaPort + "/desativarAlerta?idAlerta=" + idAlerta, String.class);
		return resp;
	}		

	

	@Override
	public List<Verificacao>obterVerificacoes(){
		logar("Obtendo verificações");

		RestTemplate rest = new RestTemplate();
		List<Verificacao> verificacoes = rest.getForObject(veriServerAddress + veriPort + "/obterVerificacoes", List.class);
		
		return verificacoes;
	}
	
	

	@Override
	public Verificacao buscarVerificacao(int idVerificacao) {

		//logar("Buscando verificação id = " + idVerificacao);
		RestTemplate rest = new RestTemplate();
		Verificacao verificacao = rest.getForObject(veriServerAddress + veriPort + "/buscarVerificacao?idVerificacao=" + idVerificacao, Verificacao.class);
		
		return verificacao;
	}
	

	
	@Override
	public String cadastrarVerificacao(Verificacao verificacao) {
		try {
			
			logar("Cadastrando verificação frequencia = " + verificacao.getFrequencia());
			
			/*Sensor sensor = this.buscarSensor(verificacao.getIdSensor());
			if (sensor == null) {
				return "Erro: Sensor não encontrado";
			}
			CondiSeguranca cond = this.buscarCondicao(verificacao.getIdCondi());
			if (cond == null) {
				return "Erro: Condição não encontrada";
			}
			Alerta alerta = this.buscarAlerta(verificacao.getIdAlerta());
			if (alerta == null) {
				return "Erro: Alerta não encontrada";
			}*/
			
			RestTemplate a = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    Gson gson = new Gson();
		    
			 HttpEntity<String> request = 
				      new HttpEntity<String>(gson.toJson(verificacao), headers);
			 ResponseEntity<String> resposta = a.postForEntity(veriServerAddress + veriPort + "/cadastrarVerificacao", request, String.class);
			 if(resposta.getStatusCode() == HttpStatus.OK) {
				 return "Sucesso";
			 }
			 return "Erro no serviço";
			 
			
		}catch(Exception e) {
			return "Erro no cadastro: + " + e.getMessage();
		}
	}
	
	
	@Override
	public String ativarVerificacao(int idVerificacao) {
		

		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(veriServerAddress + veriPort + "/ativarVerificacao?idVerificacao=" + idVerificacao, String.class);
		
		if(!resp.equalsIgnoreCase("Sucesso")) {
			return resp;
		}
		logar("Ativando verificação" + " <Verificação " + idVerificacao + ">");
		new Thread() {
		    @Override
		    public void run() {
		    	boolean keepRunning = true;
		    	int countErro = 0;
		    	do {
		    		try {
		    			
		    			//busca a verificação
		    			Verificacao verificacao = buscarVerificacao(idVerificacao);
		    			
		    			Sensor sensor = buscarSensor(verificacao.getIdSensor());
		    			if (sensor == null) {
		    				logar("Erro: Sensor não encontrado" + " <Verificação " + idVerificacao + ">");
		    				break;
		    			}
		    			CondiSeguranca condi = buscarCondicao(verificacao.getIdCondi());
		    			if (condi == null) {
		    				logar("Erro: Condição não encontrada" + " <Verificação " + idVerificacao + ">");
		    				break;
		    			}
		    			Alerta alerta = buscarAlerta(verificacao.getIdAlerta());
		    			if (alerta == null) {
		    				logar("Erro: Alerta não encontrada" + " <Verificação " + idVerificacao + ">");
		    				break;
		    			}
		    			
		    			//verifica se os valores estão dentro do esperado
		    			if(sensor.getValor() > condi.getValorMaximoSensor() || sensor.getValor() < condi.getValorMinimoSensor()) {
		    				//Verifica caso não esteja ativada 
		    				if(!alerta.getAcionado().equalsIgnoreCase("V")) {
		    					//ativa a alerta
		    					ativarAlerta(verificacao.getIdAlerta());
		    					//atribui o valor da variavel local
		    					alerta.setAcionado("V");
		    					logar("Alerta " + alerta.getId() + " disparada!" + " <Verificação " + idVerificacao + ">");
		    					//verifica o tipo de acionamento  (1 igual defesa civil)
		    					if(condi.getTipoAcionamento() == 1) {
		    						//aciona a defesa civil
		    						logar("Chamando defesa Civil" + " <Verificação " + idVerificacao + ">");
		    						Chamado chamado = new Chamado();
		    						chamado.setNome("João de Tal");
		    						chamado.setCepOrigem(alerta.getLocalizacao());
		    						chamado.setSituacao("Problema com o sensor " + sensor.getId());;
		    						String retornoDC = chamadoEmergenciaDC(chamado);
		    						logar("Retorno da Defesa Civil: " + retornoDC + " <Verificação " + idVerificacao + ">");
		    					}
		    				}
		    			//caso os valores estejam corretos
		    			}else {
		    				//Verifica se está ativada  
		    				if(alerta.getAcionado().equalsIgnoreCase("V")) {
		    					//desativa a alerta
		    					desativarAlerta(verificacao.getIdAlerta());
		    					//atribui o valor da variavel local
		    					alerta.setAcionado("F");
		    					logar("Alerta " + alerta.getId() + " parada!" + " <Verificação " + idVerificacao + ">");
		    				}
		    			}
		    			
		    			if(alerta.getAcionado().equalsIgnoreCase("V")) {
		    				logar("Alerta número " + alerta.getId() + " ativada! Verificando condições... " + " <Verificação " + idVerificacao + ">");
		    			}else {
		    				logar("Nenhuma alerta ativa! Verificando condições... " + " <Verificação " + idVerificacao + ">");
		    			}
		    			
						TimeUnit.MILLISECONDS.sleep(verificacao.getFrequencia());
						if(!verificacao.getAcionado().equalsIgnoreCase("V")) {
							keepRunning = false;
							logar("Verificação parada"  + " <Verificação " + idVerificacao + ">");
						}
						countErro = 0;
					//caso ocorra algum erro em algum serviço
					} catch (Exception e) {
						logar("Erro durante a verificação:\n" + e.getMessage()  + " <Verificação " + idVerificacao + ">" + "\nTentando novamente." + " <Verificação " + idVerificacao + ">");
						//Caso aconteça erro 5 vezes seguidas
						if(++countErro == 5) {
							logar("5 tentativas com erro. Parando o serviço" + " <Verificação " + idVerificacao + ">");	
							try {
								//tenta desativar
								desativarVerificacao(idVerificacao);
							} catch (Exception e2) {
								//caso aconteça erro na hora de desativar
								logar("Erro durante a desativação da verificação:\n" + e2.getMessage()  + " <Verificação " + idVerificacao + ">" + "\nParando aplicação" + " <Verificação " + idVerificacao + ">");
							}finally {
								keepRunning = false;
							}
						}
					}
		    		
		    		
		    	}while(keepRunning);

		    }
		  }.start();
		
		return resp;
	}
	
	@Override
	public String desativarVerificacao(int idVerificacao) {

		logar("Desativando verificação id = " + idVerificacao);
		RestTemplate rest = new RestTemplate();
		String resp = rest.getForObject(veriServerAddress + veriPort + "/desativarVerificacao?idVerificacao=" + idVerificacao, String.class);
		
		return resp;
	}

	@Override
	public Sensor buscarSensor(int idSensor) {

		//logar("Buscando sensor id = " + idSensor);
		RestTemplate rest = new RestTemplate();
		Sensor sensor = rest.getForObject(bffMoniServerAddress + bffMoniPort + "/buscarSensor?idSensor=" + idSensor, Sensor.class);
		
		return sensor;
	}
	
	@Override
	public CondiSeguranca buscarCondicao(int idCondi) {

		//logar("Buscando condição id = " + idCondi);
		RestTemplate rest = new RestTemplate();
		CondiSeguranca condicao = rest.getForObject(bffMoniServerAddress + bffMoniPort + "/buscarCondicao?idCondi=" + idCondi, CondiSeguranca.class);
		
		return condicao;
	}



	@Override
	public String chamadoEmergenciaDC(Chamado chamado) {
		logar("Chamando defesa civil nome = " + chamado.getNome());
		RestTemplate a = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    Gson gson = new Gson();
	    
		 HttpEntity<String> request = 
			      new HttpEntity<String>(gson.toJson(chamado), headers);
		 ResponseEntity<String> resposta = a.postForEntity(bffMoniServerAddress + bffMoniPort + "/chamadoEmergenciaDC", request, String.class);
		
		 return resposta.getBody();
	}



	@Override
	public String inicializar() {
		logar("Dando restart nos dados");
		//limpa os dados
		RestTemplate rest = new RestTemplate();
		
		
		rest.getForObject(alertaServerAddress + alertaPort + "/limparBase", String.class);
		rest = new RestTemplate();
		rest.getForObject(veriServerAddress + veriPort + "/limparBase", String.class);
		
		Alerta alerta = new Alerta();
		alerta.setLocalizacao("Localização 1");
		alerta.setModelo("Sirene A");
		this.cadastrarAlerta(alerta);
		
		alerta = new Alerta();
		alerta.setLocalizacao("Localização 2");
		alerta.setModelo("Sirene B");
		this.cadastrarAlerta(alerta);
		
		alerta = new Alerta();
		alerta.setLocalizacao("Localização 3");
		alerta.setModelo("Sirene C");
		this.cadastrarAlerta(alerta);
		
		alerta = new Alerta();
		alerta.setLocalizacao("Localização 4");
		alerta.setModelo("Sirene D");
		this.cadastrarAlerta(alerta);
		
		
		
		Verificacao veri = new Verificacao();
		veri.setIdAlerta(1);
		veri.setIdCondi(1);
		veri.setIdSensor(1);
		veri.setFrequencia(1000);
		this.cadastrarVerificacao(veri);
		
		veri = new Verificacao();
		veri.setIdAlerta(2);
		veri.setIdCondi(2);
		veri.setIdSensor(2);
		veri.setFrequencia(5000);
		this.cadastrarVerificacao(veri);
		
		veri = new Verificacao();
		veri.setIdAlerta(3);
		veri.setIdCondi(3);
		veri.setIdSensor(1);
		veri.setFrequencia(1000);
		this.cadastrarVerificacao(veri);
		
		veri = new Verificacao();
		veri.setIdAlerta(4);
		veri.setIdCondi(4);
		veri.setIdSensor(2);
		veri.setFrequencia(5000);
		this.cadastrarVerificacao(veri);
		
		
		return "sucesso";
	}
}