package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entities.enums.Situation;
import entities.enums.Zona;

public class Aluno {

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

	List<Mensalidade> mensalidade = new ArrayList<>();

	public Aluno() {

	}

	public Aluno(String nome, Long cpf, Date dataDeNascimento, Integer matricula, Date dataDaMatricula, char sexo,
			Long celular, String email, FormaFisica formaFisica, Endereço endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.matricula = matricula;
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

	public Mensalidade getMensalidade() {
		Date agora = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mensalidade.get(mensalidade.size() - 1).getDataDeVencimento());
		cal.add(Calendar.DAY_OF_MONTH, 15);

		Date atrasada = cal.getTime();

		if (agora.after(mensalidade.get(mensalidade.size() - 1).getDataDeVencimento())
				&& mensalidade.get(mensalidade.size() - 1).getSituation() == Situation.PAGA) {
			mensalidade.add(new Mensalidade(mensalidade.get(mensalidade.size()-1).getDataDeVencimento(), mensalidade.get(mensalidade.size() - 1).getDataDePagamento(),
					mensalidade.get(mensalidade.size() - 1).getValor()));
			mensalidade.get(mensalidade.size() - 1).setSituation(Situation.PENDENTE);
			return mensalidade.get(mensalidade.size() - 1);
		} else {
			return mensalidade.get(mensalidade.size() - 1);
		}
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

	@Override
	public String toString() {
		return "Aluno Nome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + sdf.format(dataDeNascimento)
				+ "\nMatricula: " + matricula + "\nData da Matricula: " + sdf.format(dataDaMatricula) + "\nSexo: "
				+ sexo + "\nCelular: " + celular + "\nEmail=" + email + "\nForma Fisica: " + formaFisica
				+ "\nEndereco: " + endereco + "\nMensalidade: " + mensalidade.get(mensalidade.size() - 1);
	}

}
