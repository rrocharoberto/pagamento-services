package br.inatel.dm112.services;

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
	 * Lógica de geração de pendência de pagamento (1) consulta o pedido pelo número
	 * (2) atualiza o status do pedido (3) gera o boleto (4) envia email com o pdf
	 * (5) retorna sucesso
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public PaymentStatus startPaymentOfOrder(String cpf, int orderNumber) {

		Order order = getOrder(cpf, orderNumber); // (1) consulta o pedido pelo número

		if (order.getStatus() != Order.STATUS.FILLED.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.WRONG_ORDER_STATUS);
		}

		try {
			clientOrder.startOrderPayment(orderNumber); // (2) atualiza o status do pedido
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: start payment: " + e.getMessage();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}
		BilletGenResponse respBillet;
		try {
			respBillet = clientBillet.callGenerateBilletService(orderNumber, cpf); // (3) gera o boleto
		} catch (Exception e) {
			String msg = "Erro no serviço de boleto";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.BILLET_ERROR);
		}

		byte[] PDFContent = respBillet.getPdfContent();
		try {
			clientEmail.callSendMailService(orderNumber, PDFContent); // (4) envia email com o pdf
		} catch (Exception e) {
			String msg = "Erro no serviço de email";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.EMAIL_ERROR);
		}
		System.out.println("Sucesso ao inicializar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber); // (5) retorna sucesso
	}

	/**
	 * Lógica de confirmação de pagamento (1) consulta o pedido pelo número (2)
	 * atualiza o status do pedido confirmando o pagamento (3) responde Ok
	 * 
	 * @param cpf
	 * @param orderNumber
	 * @return
	 */
	public PaymentStatus confirmPaymentOfOrder(String cpf, int orderNumber) {

		Order order = getOrder(cpf, orderNumber); // (1) consulta o pedido pelo número

		if (order.getStatus() != Order.STATUS.PENDING.ordinal()) {
			String msg = "Status do pedido " + orderNumber + " inválido: " + order.getStatus();
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.WRONG_ORDER_STATUS);
		}
		try {
			clientOrder.confirmOrderPayment(orderNumber); // (2) confirma o pagamento (e atualiza o status)
		} catch (Exception e) {
			String msg = "Erro no serviço de pedido: confirm payment";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_ERROR);
		}
		System.out.println("Sucesso ao confirmar o pagamento: orderNumber: " + orderNumber + " cpf: " + cpf);
		return new PaymentStatus(PAY_STATUS.OK.ordinal(), cpf, orderNumber); // (3) responde Ok
	}

	private Order getOrder(String cpf, int orderNumber) {
		if (cpf == null || orderNumber < 0) {
			throw PaymentStatus.createErrorStatus("CPF e pedido são obrigatórios", cpf, orderNumber,
					PAY_STATUS.NULL_VALUES);
		}
		Order order;
		try {
			order = clientOrder.retrieveOrder(orderNumber); // (1) consulta o pedido pelo número
		} catch (Exception e) {
			String msg = "Pedido " + orderNumber + " não encontrado.";
			throw PaymentStatus.createErrorStatus(msg, cpf, orderNumber, PAY_STATUS.ORDER_NOT_FOUND);
		}
		return order;
	}

}