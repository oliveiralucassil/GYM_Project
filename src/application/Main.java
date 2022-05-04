package application;

import java.text.ParseException;

import entities.Aluno;
import entities.FormaFisica;
import entities.enums.Situation;

public class Main {

	public static void main(String[] args) throws ParseException {
		String name = "Lucas";
		Aluno aluno = new Aluno(name, 001, 60.00, Situation.EM_DIA, new FormaFisica(21, 173, 900));
		
		System.out.println(aluno);
	}

}
