package com.puc.seguranca.alerta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.seguranca.alerta.dao.model.Alerta;
import com.puc.seguranca.alerta.service.IAlertaService;

@RestController
public class AlertaController {

	@Autowired
	IAlertaService aleService;

	@RequestMapping(value = "/obterAlertas", method = RequestMethod.GET)
	public List<Alerta> obterAlertas() {
		return aleService.obterAlertas();
	}
	@RequestMapping(value = "/buscarAlerta", method = RequestMethod.GET)
	public Alerta buscarAlerta(@RequestParam int idAlerta) {
		return aleService.buscarAlerta(idAlerta);
	}
	@RequestMapping(value = "/cadastrarAlerta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cadastrarAlerta(@RequestBody Alerta alerta) {
		aleService.cadastrarAlerta(alerta);
	}
	@RequestMapping(value = "/ativarAlerta", method = RequestMethod.GET)
	public String ativarAlerta(@RequestParam int idAlerta) {
		return aleService.ativarAlerta(idAlerta);
	}
	@RequestMapping(value = "/desativarAlerta", method = RequestMethod.GET)
	public String desativarAlerta(@RequestParam int idAlerta) {
		return aleService.desativarAlerta(idAlerta);
	}
	@RequestMapping(value = "/limparBase", method = RequestMethod.GET)
	public void limparBase() {
		aleService.limparBase();
	}

}