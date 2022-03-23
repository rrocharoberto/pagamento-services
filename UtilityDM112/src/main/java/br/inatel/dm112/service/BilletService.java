package br.inatel.dm112.service;

import org.springframework.stereotype.Service;

import br.inatel.dm112.adapter.BilletAdapter;
import br.inatel.dm112.adapter.model.BilletBusinessData;
import br.inatel.dm112.adapter.model.BilletContent;
import br.inatel.dm112.adapter.model.CustomerData;
import br.inatel.dm112.adapter.model.OrderData;
import br.inatel.dm112.model.BilletGenResponse;
import br.inatel.dm112.model.ResponseStatus;

@Service
public class BilletService {

	public static enum STATUS {
		OK, ERROR
	};

	public BilletGenResponse generateBillet(String orderNumber, String cpf) {
		BilletGenResponse result = new BilletGenResponse();
		try {
			byte[] pdfContent = generateBilletPDFContent(orderNumber, cpf);

			// monta a resposta
			result.setMessage("Sucesso na geração do boleto - cpf: " + cpf + " pedido: " + orderNumber);
			result.setPdfContent(pdfContent);
			result.setStatus(ResponseStatus.OK.ordinal());
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(
					"Erro gerando o boleto - cpf: " + cpf + " pedido: " + orderNumber + " - " + e.getMessage());
			result.setStatus(ResponseStatus.ERROR.ordinal());
		}
		System.out.println("BilletService - generateBillet - Result: " + result);
		return result;
	}
	
	/**
	 * Gera o pdf e obtem os bytes
	 * @param orderNumber
	 * @param cpf
	 * @return
	 */
	private byte[] generateBilletPDFContent(String orderNumber, String cpf) {

		System.out.println("Gerando boleto para cpf: " + cpf + " pedido: " + orderNumber);
		
		// obter os dados do cliente com o clientCPF através do provedor de CRM
		CustomerData customerData = retrieveCustomerData(cpf); // simulado

		// obter os dados do pedido com o orderId através do provedor de Vendas
		OrderData orderData = retrieveOrderData(); // simulado

		// obter os dados da empresa através do serviço BilletBusinessDataService
		BilletBusinessData businessData = retrieveBusinessData(orderNumber, cpf); // simulado

		// monta os dados para enviar para o adaptador
		BilletContent content = new BilletContent();
		content.setBusinessData(businessData);
		content.setOrderData(orderData);
		content.setCustomerData(customerData);

		BilletAdapter adapter = new BilletAdapter();
		byte[] pdfContent = adapter.execute(content);

		return pdfContent;
	}

	private OrderData retrieveOrderData() {
		return new OrderData(); // simulado
	}

	private CustomerData retrieveCustomerData(String cpf) {
		return new CustomerData(cpf); // simulado
	}

	private BilletBusinessData retrieveBusinessData(String orderNumber, String cpf) {
		return new BilletBusinessData(orderNumber, cpf); // simulado
	}
}