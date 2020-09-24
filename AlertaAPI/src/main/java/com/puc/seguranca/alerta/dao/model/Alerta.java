package com.puc.seguranca.alerta.dao.model;

public class Alerta {
	
	private int id;
	private String modelo;
	private String localizacao;
	private String acionado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getAcionado() {
		return acionado;
	}
	public void setAcionado(String acionado) {
		this.acionado = acionado;
	}
	
	
	
	
}
