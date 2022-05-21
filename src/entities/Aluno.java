package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entities.enums.Zona;

public class Aluno implements Serializable, Comparable<Aluno> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5908739557542836919L;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private String nome;
	private Long cpf;
	private Date dataDeNascimento;
	private Integer matricula;
	private Date dataDaMatricula;
	private char sexo;
	private Long celular;
	private String email;
	private FormaFisica formaFisica;
	private Endereço endereco;
	private Date horario;

	private List<Mensalidade> mensalidade = new ArrayList<>();

	public Aluno() {

	}

	public Aluno(String nome, Long cpf, Date dataDeNascimento, Integer matricula, Date horario, Date dataDaMatricula,
			char sexo, Long celular, String email, FormaFisica formaFisica, Endereço endereco) {

		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.matricula = matricula;
		this.horario = horario;
		this.dataDaMatricula = dataDaMatricula;
		this.sexo = sexo;
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

	public List<Mensalidade> getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(List<Mensalidade> mensalidade) {
		this.mensalidade = mensalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
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
		return "Aluno\nNOME: " + nome + "\nCPF: " + cpf + "\nDATA DE NASCIMENTO: " + sdf.format(dataDeNascimento)
				+ "\nMATRICULA: " + matricula + "\nHORARIO: " + horario + "\nDATA DA MATRICULA: "
				+ sdf.format(dataDaMatricula) + "\nSEXO: " + sexo + "\nCELULAR: " + celular + "\nEMAIL: " + email
				+ "\nFORMA FISICA: " + formaFisica + "\nENDERECO: " + endereco + "\nMENSALIDADE: "
				+ mensalidade.get(mensalidade.size() - 1);
	}

	@Override
	public int compareTo(Aluno other) {
		return nome.compareTo(other.getNome());
	}

}
