package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Aluno;
import entities.Endere?o;
import entities.FormaFisica;
import entities.enums.Zona;
import service.MonthlyService;
import service.ServiceUpdate;

public class Main {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

		List<Aluno> alunos = leitorListasAluno();
		ServiceUpdate payment = new MonthlyService();
		System.out.println("Gerenciador de Academias ");
		System.out.println("\nAdicionar aluno         (0) ");
		System.out.println("Alunos Matriculados     (1) ");
		System.out.println("Mensalidades            (2) ");
		System.out.println("Sair                    (3) ");
		System.out.println("\n---------------------------------");
		System.out.print("Entrada: ");
		int entrada = sc.nextInt();
		System.out.println("---------------------------------");
		char option = 'S';
		if (entrada == 0) {

			do {

				Integer matricula = alunos.size();

				sc.nextLine();
				System.out.print("\nNome: ");
				String nome = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Data de Nascimento (dd/MM/yyyy): ");
				Date dataDeNascimento = sdf.parse(sc.next());
				System.out.print("Sexo (M/F): ");
				char sexo = sc.next().charAt(0);
				sc.nextLine();
				System.out.print("Celular (##) #####-####: ");
				String celular = sc.nextLine();
				System.out.print("Email: ");
				String email = sc.next();
				System.out.print("Altura (cm): ");
				Integer altura = sc.nextInt();
				System.out.print("Peso (kg): ");
				Integer peso = sc.nextInt();
				sc.nextLine();
				System.out.print("Doen?a: ");
				String doenca = sc.nextLine();
				System.out.print("Horario: ");
				Date horario = sdf1.parse(sc.next());
				System.out.print("Data da Matricula: ");
				Date dataDaMatricula = sdf.parse(sc.next());
				System.out.println("\nENDERE?O ");
				System.out.print("Cidade: ");
				String cidade = sc.nextLine();
				sc.nextLine();
				System.out.print("Zona: ");
				String zona = sc.next();
				sc.nextLine();
				System.out.print("Bairro: ");
				String bairro = sc.nextLine();
				System.out.print("Rua: ");
				String rua = sc.nextLine();
				System.out.print("Numero: ");
				Integer numero = sc.nextInt();

				alunos.add(new Aluno(nome, cpf, dataDeNascimento, sexo, matricula, dataDaMatricula, celular, email,
						new FormaFisica(altura, peso, doenca),
						new Endere?o(cidade, Zona.valueOf(zona.toUpperCase()), bairro, rua, numero), horario));

				System.out.println("\nValor da Mensalidade: " + String.format("%.2f", alunos.get(alunos.size() - 1)
						.getMensalidades().get(alunos.get(alunos.size() - 1).getMensalidades().size() - 1).getValor()));
				System.out.println("\n" + alunos.get(alunos.size() - 1));

				System.out.print("\nCadastrar outro Aluno (S/N)? ");
				option = sc.next().charAt(0);

			} while (option == 'S');

		} else if (entrada == 1) {

			System.out.println("");
			Collections.sort(alunos);
			for (Aluno x : alunos) {
				payment.statusUpdate(x);
				System.out.println(
						"Nome				Data da Matricula		Data de Vencimento		Status Mensalidade(s)");
				System.out.println(x.getNome() + "		" + sdf.format(x.getDataDaMatricula()) + "			"
						+ sdf.format(x.getUltimaMensalidade().getDataDeVencimento()) + "			"
						+ x.getUltimaMensalidade().getSituation());

			}
			System.out.println();
			System.out.println("DESEJA PAGAR A MENSALIDADE DE ALGUM ALUNO ?(S/N)");
			char alterar = sc.next().charAt(0);
			if (alterar == 'S') {
				sc.nextLine();
				System.out.println("Digite o nome do aluno: ");
				String procurar = sc.nextLine();
				for (Aluno x : alunos) {
					if (procurar.compareTo(x.getNome()) == 0) {
						payment.effectPayment(x);
						System.out.println();
						System.out.println(
								"Nome				Data da Matricula		Data de Vencimento		Status Mensalidade(s)");
						System.out.println(x.getNome() + "		" + sdf.format(x.getDataDaMatricula()) + "			"
								+ sdf.format(
										x.getMensalidades().get(x.getMensalidades().size() - 1).getDataDeVencimento())
								+ "			" + x.getMensalidades().get(x.getMensalidades().size() - 1).getSituation());
					}
				}
			}

		}
		gravadorListaAluno(alunos);
		sc.close();
	}

	private static void gravadorListaAluno(List<Aluno> alunos) {
		try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream("alunosgym.bin"))) {
			obs.writeObject(alunos);
		} catch (IOException o) {
			o.printStackTrace();
		}
	}

	@SuppressWarnings({"unchecked"})
	private static ArrayList<Aluno> leitorListasAluno() {
		ArrayList<Aluno> alunos = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("alunosgym.bin"))) {

			alunos = (ArrayList<Aluno>) ois.readObject();
			return alunos;

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return alunos;
	}
}
