package entities;

public class FormaFisica {
	private Integer idade;
	private Integer altura;
	private Integer peso;

	public FormaFisica() {
	}

	public FormaFisica(Integer idade, Integer altura, Integer peso) {
		this.idade = idade;
		this.altura = altura;
		this.peso = peso;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
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
