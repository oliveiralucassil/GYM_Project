package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.enums.Situation;

public class Aluno {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM");
	
	private String nome;
	private Integer matricula;
	private Double mensalidade;
	private Situation situation;
	private Date dataDaMatricula;
	private Date dataDaMensalidade;
	private FormaFisica formaFisica;

	public Aluno() {
	}


	public Aluno(String nome, Integer matricula, Double mensalidade, Situation situation,
			FormaFisica formaFisica) throws ParseException {
		this.nome = nome;
		this.matricula = matricula;
		this.mensalidade = mensalidade;
		this.situation = situation;
		this.dataDaMatricula =  new Date();
		this.formaFisica = formaFisica;
		String dataDaMensalidade = sdf1.format(new Date());
		this.dataDaMensalidade = sdf1.parse(dataDaMensalidade);
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Double mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public Date getDataDaMatricula() {
		return dataDaMatricula;
	}

	public Date getDataDaMensalidade() {
		return dataDaMensalidade;
	}


	public void setDataDaMensalidade(Date dataDaMensalidade) {
		this.dataDaMensalidade = dataDaMensalidade;
	}


	public FormaFisica getFormaFisica() {
		return formaFisica;
	}

	public void setFormaFisica(FormaFisica formaFisica) {
		this.formaFisica = formaFisica;
	}


	@Override
	public String toString() {
		return 	"Aluno: " +  nome +
				"\nData da Mensalidade: " + sdf1.format(dataDaMensalidade)+
				"\nData da Matricula: " + sdf.format(dataDaMatricula);
	}
	

	
}
