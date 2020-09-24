package com.puc.moitoramento.BFFSeguranca.model;

public class CondiSeguranca {
	
	private int id;
	private String nomeCondi;
	private int idSensor;
	private double valorMaximoSensor;
	private double valorMinimoSensor;
	private int tipoAcionamento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCondi() {
		return nomeCondi;
	}
	public void setNomeCondi(String nomeCondi) {
		this.nomeCondi = nomeCondi;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public double getValorMaximoSensor() {
		return valorMaximoSensor;
	}
	public void setValorMaximoSensor(double valorMaximoSensor) {
		this.valorMaximoSensor = valorMaximoSensor;
	}
	public double getValorMinimoSensor() {
		return valorMinimoSensor;
	}
	public void setValorMinimoSensor(double valorMinimoSensor) {
		this.valorMinimoSensor = valorMinimoSensor;
	}
	public int getTipoAcionamento() {
		return tipoAcionamento;
	}
	public void setTipoAcionamento(int tipoAcionamento) {
		this.tipoAcionamento = tipoAcionamento;
	}

	

}
