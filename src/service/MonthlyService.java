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
		
		/*
		 * Este if é para percorrer a ultima mensalidade da lista de mensalidades e
		 * conferir se ela esta paga, caso não esteja, será acrescentado um mês a data
		 * de vencimento, e a data de pagamento sera atualizado para o momento do
		 * gamento. Caso esteja pago será criada uma nova mensalidade do proximo mês ja
		 * paga (sendo que o pagamento da mensalidade paga o mês que vc irá treinar, não
		 * o que já treino).
		 */

		if (aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getSituation() != Situation.PAGA) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);

			Date novaData = cal.getTime();

			aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setDataDeVencimento(novaData);
			aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setDataDePagamento(moment);
			aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.PAGA);

			System.out.println("Mensalidade Paga com sucesso! ");
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento());
			cal.add(Calendar.MONTH, 1);

			Date novaData = cal.getTime();
			aluno.addMensalidade(new Mensalidade(novaData, moment,
					aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getValor()));

			System.out.println("Proxima mensalidade paga com sucesso! ");
		}
	}

	@Override
	public void statusUpdate(Aluno aluno) {
		Date agora = new Date();
		Calendar cal = Calendar.getInstance();

		cal.setTime(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento());
		cal.add(Calendar.DAY_OF_MONTH, 15);

		Date atrasada = cal.getTime();
		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento());
		cal1.add(Calendar.MONTH, 1);
		Date canselada = cal1.getTime();
		
		/*
		 * Este if verifica se a data atual ja passou da data de vencimento e a anterior
		 * esta paga, sendo assim ele cria uma nova mensalidade do mês atual com o
		 * status de "pendente" caso esteja no prazo de somente 15 dias de após a data
		 * de vencimento, "atrasada" caso ultrapasse o prazo este prazo de 15 dias, e
		 * "caselada" caso ultrapasse 1 mês após a data de vencimento.
		 * 
		 * Agora se a data anterior não estiver paga e sim com algum dos outros status,
		 * somente será modificado o status conforme os prazos
		 */

		if (agora.after(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento())
				&& aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getSituation() == Situation.PAGA) {

			aluno.addMensalidade(
					new Mensalidade(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento(),
							aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDePagamento(),
							aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getValor()));

			if (agora.after(atrasada) && agora.before(canselada)) {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.ATRASADA);
			} else if (agora.compareTo(canselada) >= 0) {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.TREINO_CANSELADO);
			} else {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.PENDENTE);
			}

		} else if (agora.after(aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getDataDeVencimento())
				&& aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).getSituation() != Situation.PAGA) {

			if (agora.after(atrasada) && agora.before(canselada)) {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.ATRASADA);
			} else if (agora.after(canselada)) {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.TREINO_CANSELADO);
			} else {
				aluno.getMensalidade().get(aluno.getMensalidade().size() - 1).setSituation(Situation.PENDENTE);
			}

		} else {
			aluno.getMensalidade().get(aluno.getMensalidade().size() - 1);
		}
	}
}
