package com.puc.seguranca.ejbMonitoramento.model;

public class Verificacao {
	
	private int id;
	private int idCondi ;
	private int idAlerta ;
	private int idSensor ;
	private int frequencia;
	private String acionado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCondi() {
		return idCondi;
	}
	public void setIdCondi(int idCondi) {
		this.idCondi = idCondi;
	}
	public int getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(int idAlerta) {
		this.idAlerta = idAlerta;
	}
	public int getIdSensor() {
		return idSensor;
	}
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public String getAcionado() {
		return acionado;
	}
	public void setAcionado(String acionado) {
		this.acionado = acionado;
	}
	
}
