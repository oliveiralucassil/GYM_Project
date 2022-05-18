package service;

import java.util.Calendar;
import java.util.Date;

import entities.Aluno;
import entities.Mensalidade;
import entities.enums.Situation;

public class MonthlyService {
	
	private Date moment = new Date();
	
	public void effectPayment(Aluno aluno) {
		
		if (aluno.getMensalidade().getSituation() != Situation.PAGA) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getMensalidade().getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);
			
			Date novaData = cal.getTime();
			
			aluno.getMensalidade().setDataDeVencimento(novaData);
			aluno.getMensalidade().setDataDePagamento(moment);
			aluno.getMensalidade().setSituation(Situation.PAGA);
			
			System.out.println("Mensalidade Paga com sucesso! ");
		}
		else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getMensalidade().getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);
			
			Date novaData = cal.getTime();
			aluno.addMensalidade(new Mensalidade(novaData, moment, aluno.getMensalidade().getValor()));
			
			System.out.println("Proxima mensalidade paga com sucesso! ");
		}
	}
	public void statusUpdate(Aluno aluno) {
		
	}
}
