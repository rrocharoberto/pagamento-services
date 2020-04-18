package br.inatel.dm112.service;

import org.springframework.stereotype.Service;

import br.inatel.dm112.adapter.BilletAdapter;
import br.inatel.dm112.adapter.model.BilletBusinessData;
import br.inatel.dm112.adapter.model.BilletContent;
import br.inatel.dm112.adapter.model.CustomerData;
import br.inatel.dm112.adapter.model.OrderData;

@Service
public class BilletService {

	public static enum STATUS {
		OK, ERROR
	};

	public byte[] generateBillet(String orderNumber, String cpf) {

		System.out.println("Gerando boleto para cpf: " + cpf + " pedido: " + orderNumber);
		
		BilletAdapter adapter = new BilletAdapter();
		
		// obter os dados do cliente com o clientCPF através do provedor de
		// CRM
		CustomerData customerData = retrieveCustomerData(cpf); // simulado

		// obter os dados do pedido com o orderId através do provedor de
		// Vendas
		OrderData orderData = retrieveOrderData(); // simulado

		// obter os dados da empresa através do serviço
		// BilletBusinessDataService
		BilletBusinessData businessData = retrieveBusinessData(orderNumber, cpf); // simulado

		// monta os dados para enviar para o adaptador
		BilletContent content = new BilletContent();
		content.setBusinessData(businessData);
		content.setOrderData(orderData);
		content.setCustomerData(customerData);

		// gera o pdf e obtem os bytes
		byte[] pdfContent = adapter.execute(content);

		// retorna o pdf
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