package entities;

import java.io.Serializable;

import entities.enums.Zona;

public class Endereço implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7979266061166888426L;
	private String cidade;
	private Zona zona;
	private String bairro;
	private String rua;
	private Integer numero;

	public Endereço() {

	}

	public Endereço(String cidade, Zona zona, String bairro, String rua, Integer numero) {

		this.cidade = cidade;
		this.zona = zona;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Cidade: " + cidade + ", Zona: " + zona + ", Bairro: " + bairro + ", Rua: " + rua + ", Numero: "
				+ numero;
	}
	
	

}
