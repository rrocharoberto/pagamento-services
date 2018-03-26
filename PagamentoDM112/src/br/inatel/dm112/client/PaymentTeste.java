package br.inatel.dm112.client;

import java.util.Date;

import br.inatel.dm112.client.billet.stub.BilletGenResponse;
import br.inatel.dm112.client.mail.stub.MailStatusResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.model.ResponseStatus;


public class PaymentTeste {

	//TODO: Fique à vontade para alterar estes atributos
	//TODO: Para enviar um email através do gmail, é necessário habilitar o SMTP da conta de envio.
	private String sendToAddress = "rrocha.roberto@gmail.com";
	private String sendFromAddress = "robertorr9@gmail.com";
	private String sendPassAddress = "robertodm112";

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
				BilletGenResponse respBillet = clientBillet.callGenerateBilletService(orderNumber, cpf); //gera o boleto
			
				if(respBillet.getStatus() == ResponseStatus.OK.ordinal()) {//OK
					byte [] PDFContent = respBillet.getPdfContent();
					MailStatusResponse respEmail = clientEmail.callSendMailService( //envia email com o pdf
							sendFromAddress, sendPassAddress, sendToAddress , PDFContent);
				
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