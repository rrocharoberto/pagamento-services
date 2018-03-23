package br.inatel.dm112.services;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.client.BilletClient;
import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.client.billet.stub.BilletGenData;
import br.inatel.dm112.client.mail.stub.MailStatusResponse;
import br.inatel.dm112.interfaces.Payment;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.model.ResponseStatus;

@Path("/")
public class PaymentImpl implements Payment {

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/startPaymentOfOrder/{cpf}/{orderNumber}")
	public PaymentStatus startPaymentOfOrder(@PathParam("cpf")String cpf, 
												@PathParam("orderNumber")String orderNumber) {
		if (cpf == null || orderNumber == null) {
			return new PaymentStatus(ResponseStatus.ERROR.ordinal(), cpf, orderNumber);
		}
		//lógica de pendência de pagamento
		//instancia os clientes
		OrderClient clientOrder = new OrderClient();
		BilletClient clientBillet = new BilletClient();
		EmailClient clientEmail = new EmailClient();

		Order order = clientOrder.retrieveOrder(orderNumber); //consulta o pedido pelo número

		if(order != null) { //alguma hora vai ser preciso verificar o status do pedido aqui
			order.setIssueDate(new Date());
			order.setStatus(Order.STATUS.PENDING.ordinal()); //pendente de pagamento
			OrderResponse respOrder = clientOrder.updateOrder(order); //atualiza o status do pedido

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

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/confirmPaymentOfOrder/{cpf}/{orderNumber}")
	public PaymentStatus confirmPaymentOfOrder(@PathParam("cpf") String cpf, 
												@PathParam("orderNumber") String orderNumber) {
		if (cpf == null || orderNumber == null) {
			return new PaymentStatus(ResponseStatus.ERROR.ordinal(), cpf, orderNumber);
		}
		//lógica de confirmação de pagamento
		
		OrderClient clientOrder = new OrderClient();

		Order order = clientOrder.retrieveOrder(orderNumber); //consulta o pedido pelo número

		if(order != null) { //alguma hora vai ser preciso verificar o status do pedido aqui
			order.setPaymentDate(new Date());
			order.setStatus(Order.STATUS.CONFIRMED.ordinal()); //confirma o pagamento
			OrderResponse respOrder = clientOrder.updateOrder(order); //atualiza o status do pedido

			if(respOrder.getStatus() == ResponseStatus.OK.ordinal()) { //OK
				
			} else {
				System.out.println("Erro no serviço de pedido: update");
			}
		} else {
			System.out.println("Erro no serviço de pedido: get");
		}
		
		return new PaymentStatus(ResponseStatus.OK.ordinal(), cpf, orderNumber);
	}

}