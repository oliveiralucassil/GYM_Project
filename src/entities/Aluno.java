package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entities.enums.Zona;

public class Aluno extends Pessoa implements Serializable, Comparable<Aluno>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5908739557542836919L;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Integer matricula;
	private Date dataDaMatricula;
	private String celular;
	private String email;
	private FormaFisica formaFisica;
	private Endereço endereco;
	private Date horario;

	private List<Mensalidade> mensalidade = new ArrayList<>();

	public Aluno() {

	}

	public Aluno(String nome, String cpf, Date dataDeNascimento, char sexo, Integer matricula, Date dataDaMatricula,
			String celular, String email, FormaFisica formaFisica, Endereço endereco, Date horario) {
		super(nome, cpf, dataDeNascimento, sexo);
		this.matricula = matricula;
		this.horario = horario;
		this.dataDaMatricula = dataDaMatricula;
		this.celular = celular;
		this.email = email;
		this.formaFisica = formaFisica;
		this.endereco = endereco;

		Calendar cal = Calendar.getInstance();
		cal.setTime(dataDaMatricula);
		cal.add(Calendar.MONTH, 1);
		Date dataDeVencimento = cal.getTime();

		Double valor = 0.0;
		if (endereco.getZona().compareTo(Zona.RURAL) == 0) {
			valor = 55.00;
		} else {
			valor = 60.00;
		}

		mensalidade.add(new Mensalidade(dataDeVencimento, dataDaMatricula, valor));

	}

	public List<Mensalidade> getMensalidades() {
		return mensalidade;
	}

	public void setMensalidade(List<Mensalidade> mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Mensalidade getUltimaMensalidade() {
		return this.getMensalidades().get(getMensalidades().size() - 1);
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Date getDataDaMatricula() {
		return dataDaMatricula;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public void setDataDaMatricula(Date dataDaMatricula) {
		this.dataDaMatricula = dataDaMatricula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FormaFisica getFormaFisica() {
		return formaFisica;
	}

	public void setFormaFisica(FormaFisica formaFisica) {
		this.formaFisica = formaFisica;
	}

	public Endereço getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereço endereco) {
		this.endereco = endereco;
	}

	public void addMensalidade(Mensalidade mensalide) {
		mensalidade.add(mensalide);
	}

	@Override
	public String toString() {
		return "Aluno\nNOME: " + getNome() + "\nCPF: " + getCpf() + "\nDATA DE NASCIMENTO: "
				+ sdf.format(getDataDeNascimento()) + "\nMATRICULA: " + matricula + "\nHORARIO: " + horario
				+ "\nDATA DA MATRICULA: " + sdf.format(dataDaMatricula) + "\nSEXO: " + getSexo() + "\nCELULAR: "
				+ celular + "\nEMAIL: " + email + "\nFORMA FISICA: " + formaFisica + "\nENDERECO: " + endereco
				+ "\nMENSALIDADE: " + mensalidade.get(mensalidade.size() - 1);
	}
	@Override
	public int compareTo(Aluno other) {
		return getNome().compareTo(other.getNome());
	}
}
