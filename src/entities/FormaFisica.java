package entities;

import java.io.Serializable;

public class FormaFisica implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1907447673479223618L;
	
	private Integer altura;
	private Integer peso;
	private String doenca;

	public FormaFisica() {
	}

	public FormaFisica(Integer altura, Integer peso, String doenca) {
		this.altura = altura;
		this.peso = peso;
		this.doenca = doenca;
	}

	public String getDoenca() {
		return doenca;
	}

	public void setDoenca(String doenca) {
		this.doenca = doenca;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Altura: " + altura + ", Peso: " + peso + ", Doenca: " + doenca;
	}
	

}
