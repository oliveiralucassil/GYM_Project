package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Aluno;
import entities.Endereço;
import entities.FormaFisica;
import entities.enums.Situation;
import entities.enums.Zona;

public class Main {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String nome;
		Long cpf;
		Date dataDeNascimento;
		Date dataDaMatricula = new Date();
		Integer matricula;
		char sexo;
		Long celular;
		String email;
		Integer altura;
		Integer peso;
		String doenca;
		String cidade;
		String zona;
		String bairro;
		String rua;
		Integer numero;
		
		System.out.print("\nNome: ");
		nome = sc.nextLine();
		System.out.print("\nCPF: ");
		cpf = sc.nextLong();
		System.out.print("\nData de Nascimento (dd/MM/yyyy): ");
		dataDeNascimento = sdf.parse(sc.next());
		System.out.print("\nSexo (M/F): ");
		sexo = sc.next().charAt(0);
		System.out.print("\nCelular (##) #####-####: ");
		celular = sc.nextLong();
		System.out.print("\nEmail: ");
		email = sc.next();
		System.out.print("\nAltura (cm): ");
		altura = sc.nextInt();
		System.out.print("\nPeso (kg): ");
		peso = sc.nextInt();
		System.out.print("\nDoença: ");
		doenca = sc.nextLine();
		System.out.println("\n\nEndereço: ");
		System.out.print("Cidade: ");
		cidade = sc.nextLine();
		System.out.println("\nZona: ");
		zona = sc.next();
		System.out.print("\nBairro: ");
		bairro = sc.nextLine();
		System.out.print("\nRua: ");
		rua = sc.nextLine();
		System.out.print("\nNumero: ");
		numero = sc.nextInt();
		
		Aluno aluno = new Aluno(nome, cpf, dataDeNascimento,1, dataDaMatricula, sexo, celular, email, 
				new FormaFisica(altura, peso, doenca), new Endereço(cidade, Zona.valueOf(zona), bairro, rua, numero));
		
		System.out.println(sdf.format(aluno.getMensalidade().get(0).getDataDeVencimento()));

		
	}

}
