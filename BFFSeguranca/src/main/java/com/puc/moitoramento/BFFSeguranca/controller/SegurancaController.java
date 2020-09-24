package com.puc.moitoramento.BFFSeguranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.moitoramento.BFFSeguranca.model.Alerta;
import com.puc.moitoramento.BFFSeguranca.model.Verificacao;
import com.puc.moitoramento.BFFSeguranca.service.ISegurancaService;

@RestController
public class SegurancaController {

	@Autowired
	ISegurancaService segService;

	@RequestMapping(value = "/inicializar", method = RequestMethod.GET)
	public String inicializar() {
		return segService.inicializar();
	}
	
	@RequestMapping(value = "/obterAlertas", method = RequestMethod.GET)
	public List<Alerta> obterAlertas() {
		return segService.obterAlertas();
	}
	
	@RequestMapping(value = "/buscarAlerta", method = RequestMethod.GET)
	public Alerta buscarAlerta(@RequestParam int idAlerta) {
		return segService.buscarAlerta(idAlerta);
	}


	@RequestMapping(value = "/cadastrarAlerta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cadastrarAlerta(@RequestBody Alerta alerta) {
		return segService.cadastrarAlerta(alerta);
	}
	

	@RequestMapping(value = "/obterVerificacoes", method = RequestMethod.GET)
	public List<Verificacao> obterVerificacoes() {
		return segService.obterVerificacoes();
	}
	
	@RequestMapping(value = "/buscarVerificacao", method = RequestMethod.GET)
	public Verificacao buscarVerificacao(@RequestParam int idVerificacao) {
		return segService.buscarVerificacao(idVerificacao);
	}


	@RequestMapping(value = "/cadastrarVerificacao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String cadastrarVerificacao(@RequestBody Verificacao verificacao) {
		return segService.cadastrarVerificacao(verificacao);
	}
	
	@RequestMapping(value = "/ativarVerificacao", method = RequestMethod.GET)
	public String ativarVerificacao(@RequestParam int idVerificacao) {
		return segService.ativarVerificacao(idVerificacao);
	}
	@RequestMapping(value = "/desativarVerificacao", method = RequestMethod.GET)
	public String desativarVerificacao(@RequestParam int idVerificacao) {
		return segService.desativarVerificacao(idVerificacao);
	}
	

}