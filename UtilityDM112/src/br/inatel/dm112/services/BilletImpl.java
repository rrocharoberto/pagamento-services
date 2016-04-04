package br.inatel.dm112.services;

import javax.jws.WebService;

import br.inatel.dm112.adapter.BilletAdapter;
import br.inatel.dm112.adapter.model.BilletBusinessData;
import br.inatel.dm112.adapter.model.BilletContent;
import br.inatel.dm112.adapter.model.CustomerData;
import br.inatel.dm112.adapter.model.OrderData;
import br.inatel.dm112.interfaces.Billet;
import br.inatel.dm112.services.entity.BilletGenResponse;

@WebService(targetNamespace = "dm112", serviceName = "billetService")
public class BilletImpl implements Billet {
	
	public static enum STATUS { OK, ERROR };

	@Override
	public BilletGenResponse generateBillet(String orderNumber, String cpf) {

		BilletAdapter adapter = new BilletAdapter();
		BilletGenResponse result = new BilletGenResponse();
		try {
			System.out.println("Gerando boleto para cpf: " + cpf + " pedido: " + orderNumber);
			// obter os dados do cliente com o clientCPF através do provedor de
			// CRM
			CustomerData customerData = retrieveCustomerData(cpf); // simulado

			// obter os dados do pedido com o orderId através do provedor de
			// Vendas
			OrderData orderData = retrieveOrderData(); // simulado

			// obter os dados da empresa através do serviço
			// BilletBusinessDataService
			BilletBusinessData businessData = retrieveBusinessData(orderNumber, cpf); //simulado

			// monta os dados para enviar para o adaptador
			BilletContent content = new BilletContent();
			content.setBusinessData(businessData);
			content.setOrderData(orderData);
			content.setCustomerData(customerData);

			// gera o pdf e obtem os bytes
			byte[] pdfContent = adapter.execute(content);

			// monta a resposta
			result.setMessage("Sucesso na geração do boleto - cpf: " + cpf + " pedido: " + orderNumber);
			result.setPdfContent(pdfContent);
			result.setStatus(BilletImpl.STATUS.OK.ordinal());
		} catch (Exception e) {
			result.setMessage(
					"Erro gerando o boleto - cpf: " + cpf + " pedido: " + orderNumber + " - " + e.getMessage());
			result.setStatus(BilletImpl.STATUS.ERROR.ordinal());
		}
		System.out.println(result.getMessage());
		return result;
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