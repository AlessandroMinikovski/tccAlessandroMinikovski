package com.puc.seguranca.verificacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.seguranca.verificacao.dao.model.Verificacao;
import com.puc.seguranca.verificacao.service.IVerificacaoService;

@RestController
public class VerificacaoController {

	@Autowired
	IVerificacaoService veriService;

	@RequestMapping(value = "/obterVerificacoes", method = RequestMethod.GET)
	public List<Verificacao> obterVerificacoes() {
		return veriService.obterVerificacoes();
	}
	@RequestMapping(value = "/buscarVerificacao", method = RequestMethod.GET)
	public Verificacao buscarVerificacao(@RequestParam int idVerificacao) {
		return veriService.buscarVerificacao(idVerificacao);
	}
	@RequestMapping(value = "/cadastrarVerificacao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrarVerificacao(@RequestBody Verificacao verificacao) {
		veriService.cadastrarVerificacao(verificacao);
	}
	@RequestMapping(value = "/ativarVerificacao", method = RequestMethod.GET)
	public String ativarVerificacao(@RequestParam int idVerificacao) {
		return veriService.ativarVerificacao(idVerificacao);
	}
	@RequestMapping(value = "/desativarVerificacao", method = RequestMethod.GET)
	public String desativarVerificacao(@RequestParam int idVerificacao) {
		return veriService.desativarVerificacao(idVerificacao);
	}
	@RequestMapping(value = "/limparBase", method = RequestMethod.GET)
	public void limparBase() {
		veriService.limparBase();
	}
}