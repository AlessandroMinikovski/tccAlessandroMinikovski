package com.puc.monitoramento.condiseguranca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.monitoramento.condiseguranca.dao.model.CondiSeguranca;
import com.puc.monitoramento.condiseguranca.service.ICondiSegurancaService;

@RestController
public class CondiSegurancaController {

	@Autowired
	ICondiSegurancaService monService;

	@RequestMapping(value = "/obterCondicoes", method = RequestMethod.GET)
	public List<CondiSeguranca> obterCondicoes() {
		return monService.obterCondicoes();
	}
	

	@RequestMapping(value = "/buscarCondicao", method = RequestMethod.GET)
	public CondiSeguranca buscarCondicao(@RequestParam int idCondi) {
		return monService.buscarCondicao(idCondi);
	}


	@RequestMapping(value = "/cadastrarCondicoes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrarCondicoes(@RequestBody CondiSeguranca condi) {
		monService.cadastrarCondicoes(condi);
	}

	@RequestMapping(value = "/limparBase", method = RequestMethod.GET)
	public void limparBase() {
		 monService.limparBase();
	}
}