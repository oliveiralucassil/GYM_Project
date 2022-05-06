package entities;

public class FormaFisica {
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

}
