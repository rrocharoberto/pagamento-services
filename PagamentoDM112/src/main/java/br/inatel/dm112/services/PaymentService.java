package br.inatel.dm112.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.client.BilletClient;
import br.inatel.dm112.client.EmailClient;
import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.BilletGenResponse;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.model.PaymentStatus.PAY_STATUS;

@Service
public class PaymentService {

	@Autowired
	private OrderClient clientOrder;

	@Autowired
	private BilletClient clientBillet;
	
	@Autowired
	private EmailClient clientEmail;

	/**
	 * Lógica de geração de pendência de pagamento
	 * (1) consulta o pedido pelo número
	 * (2) atualiza o status do pedido
	 * (3) gera o boleto
	 * (4) envia email com o pdf
	 * (5) retorna sucesso
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public PaymentStatus startPaymentOfOrder(String cpf, int orderNumber) {
		
		if (cpf == null || orderNumber < 0) {
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.NULL_VALUES);
		}
		Order order;
		try {
			order = clientOrder.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número
		} catch(Exception e ) {
			System.out.println("Order " + orderNumber + " not found.");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.ORDER_NOT_FOUND);
		}
		
		if(order.getStatus() != Order.STATUS.PENDING.ordinal()) {
			System.out.println("Invalid order status: " + orderNumber + ": " + order.getStatus());
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.WRONG_ORDER_STATUR);
		}

		order.setIssueDate(new Date());
		order.setStatus(Order.STATUS.PENDING.ordinal()); //pendente de pagamento
		
		try {
			clientOrder.updateOrder(order); //(2) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}
		BilletGenResponse respBillet;
		try {
			respBillet = clientBillet.callGenerateBilletService(orderNumber, cpf); //(3) gera o boleto
		} catch(Exception e ) {
			System.out.println("Erro no serviço de boleto");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.BILLET_ERROR);
		}
		
		byte [] PDFContent = respBillet.getPdfContent();
		try {
			clientEmail.callSendMailService(orderNumber, PDFContent); //(4) envia email com o pdf
		} catch(Exception e ) {
			System.out.println("Erro no serviço de email");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.EMAIL_ERROR);
		}
		System.out.println("Sucesso ao inicializar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber); //(5) retorna sucesso
	}

	/**
	 * Lógica de confirmação de pagamento
	 * (1) consulta o pedido pelo número
	 * (2) confirma o pagamento
	 * (3) atualiza o status do pedido
	 * (4) responde Ok
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public PaymentStatus confirmPaymentOfOrder(String cpf, int orderNumber) {
		
		if (cpf == null || orderNumber < 0) {
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.NULL_VALUES);
		}
		
		Order order = clientOrder.retrieveOrder(orderNumber); //(1) consulta o pedido pelo número

		if(order == null) { //alguma hora vai ser preciso verificar o status do pedido aqui
			System.out.println("Erro no serviço de pedido: order is null.");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.ORDER_NOT_FOUND);
		}
		order.setPaymentDate(new Date());
		order.setStatus(Order.STATUS.CONFIRMED.ordinal()); //(2) confirma o pagamento
		try {
			clientOrder.updateOrder(order); //(3) atualiza o status do pedido
		} catch(Exception e ) {
			System.out.println("Erro no serviço de pedido: update");
			return PaymentStatus.createErrorStatus(cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber); //(4) responde Ok
	}
}