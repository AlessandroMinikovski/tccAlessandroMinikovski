package com.puc.seguranca.ejbMonitoramento.model;

public class Sensor {
	private int id;
	private String modelo;
	private String finalidade;
	private String tipoConexao;
	private double valor;
	
	
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
	public String getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}
	public String getTipoConexao() {
		return tipoConexao;
	}
	public void setTipoConexao(String tipoConexao) {
		this.tipoConexao = tipoConexao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	
}
