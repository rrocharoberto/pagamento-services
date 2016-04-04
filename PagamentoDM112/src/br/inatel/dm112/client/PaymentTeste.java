package br.inatel.dm112.client;

import java.util.Date;

import br.inatel.dm112.client.billet.stub.BilletGenData;
import br.inatel.dm112.client.mail.stub.MailStatusResponse;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.model.entities.Order;


public class PaymentTeste {

	public PaymentStatus startPaymentOfOrder(String cpf, String orderNumber) {
		if (cpf == null || orderNumber == null) {
			return new PaymentStatus(ResponseStatus.ERROR.ordinal(), cpf, orderNumber);
		}

		//instancia os clientes
		OrderClient clientOrder = new OrderClient();
		BilletClient clientBillet = new BilletClient();
		EmailClient clientEmail = new EmailClient();

		Order order = clientOrder.retrieveOrder(orderNumber); //consulta o pedido pelo número

		if(order != null) {
			order.setIssueDate(new Date());
			order.setStatus(Order.STATUS.PENDING.ordinal()); //pendente de pagamento
			OrderResponse respOrder = clientOrder.updateOrder(order); //atualiza o status do pedido com

			if(respOrder.getStatus() == ResponseStatus.OK.ordinal()) { //OK
				BilletGenData respBillet = clientBillet.callGenerateBilletService(orderNumber, cpf); //gera o boleto
			
				if(respBillet.getStatus() == ResponseStatus.OK.ordinal()) {//OK
					byte [] PDFContent = respBillet.getPdfContent();
					MailStatusResponse respEmail = clientEmail.callSendMailService( //envia email com o pdf
							"robertorr9@gmail.com", "robertodm112","rrocha.roberto@gmail.com", PDFContent);
				
					if(respEmail.getStatus() == ResponseStatus.OK.ordinal()) {//OK
						return new PaymentStatus(ResponseStatus.OK.ordinal(), cpf, orderNumber); //Retorna sucesso
					} else {
						System.out.println("Erro no serviço de email");
					}
				} else {
					System.out.println("Erro no serviço de boleto");
				}
			} else {
				System.out.println("Erro no serviço de pedido: update");
			}
		} else {
			System.out.println("Erro no serviço de pedido: get");
		}
		
		return new PaymentStatus(ResponseStatus.ERROR.ordinal(), cpf, orderNumber);
	}

	public static void main(String[] args) {
		PaymentTeste pt = new PaymentTeste();
		pt.startPaymentOfOrder("111.111.111-11", "456");
	}

}