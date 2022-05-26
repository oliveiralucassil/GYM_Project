package service;

import java.util.Calendar;
import java.util.Date;

import entities.Aluno;
import entities.Mensalidade;
import entities.enums.Situation;

public class MonthlyService implements ServiceUpdate {

	private Date moment = new Date();

	@Override
	public void effectPayment(Aluno aluno) {
		

		if (aluno.getUltimaMensalidade().getSituation() != Situation.PAGA) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getUltimaMensalidade().getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);

			Date novaData = cal.getTime();

			aluno.getUltimaMensalidade().setDataDeVencimento(novaData);
			aluno.getUltimaMensalidade().setDataDePagamento(moment);
			aluno.getUltimaMensalidade().setSituation(Situation.PAGA);

			System.out.println("Mensalidade Paga com sucesso! ");
			
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getUltimaMensalidade().getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);

			Date novaData = cal.getTime();
			aluno.addMensalidade(new Mensalidade(novaData, moment,
					aluno.getUltimaMensalidade().getValor()));

			System.out.println("Proxima mensalidade paga com sucesso! ");
		}
	}

	@Override
	public void statusUpdate(Aluno aluno) {
		Date agora = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(aluno.getUltimaMensalidade().getDataDeVencimento());
		cal.add(Calendar.DAY_OF_MONTH, 15);

		Date atrasada = cal.getTime();
		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(aluno.getUltimaMensalidade().getDataDeVencimento());
		cal1.add(Calendar.MONTH, 1);
		Date cancelada = cal1.getTime();

		
		if (agora.after(aluno.getUltimaMensalidade().getDataDeVencimento())
				&& aluno.getUltimaMensalidade().getSituation() == Situation.PAGA) {

			aluno.addMensalidade(
					new Mensalidade(aluno.getUltimaMensalidade().getDataDeVencimento(),
							aluno.getUltimaMensalidade().getDataDePagamento(),
							aluno.getUltimaMensalidade().getValor()));

			if (agora.after(atrasada) && agora.before(cancelada)) {
				aluno.getUltimaMensalidade().setSituation(Situation.ATRASADA);
			} else if (agora.compareTo(cancelada) >= 0) {
				aluno.getUltimaMensalidade().setSituation(Situation.TREINO_CANSELADO);
			} else {
				aluno.getUltimaMensalidade().setSituation(Situation.PENDENTE);
			}

		} else if (agora.after(aluno.getUltimaMensalidade().getDataDeVencimento())
				&& aluno.getUltimaMensalidade().getSituation() != Situation.PAGA) {

			if (agora.after(atrasada) && agora.before(cancelada)) {
				aluno.getUltimaMensalidade().setSituation(Situation.ATRASADA);
			} else if (agora.after(cancelada)) {
				aluno.getUltimaMensalidade().setSituation(Situation.TREINO_CANSELADO);
			} else {
				aluno.getUltimaMensalidade().setSituation(Situation.PENDENTE);
			}

		}
	}
}
