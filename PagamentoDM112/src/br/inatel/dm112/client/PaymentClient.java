package br.inatel.dm112.client;

import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.services.PaymentService;

public class PaymentClient {

	public static void main(String[] args) {
		PaymentService service = new PaymentService();
		PaymentStatus result = service.startPaymentOfOrder("111.111.111-11", 456);
		System.out.println(result);
	}

}