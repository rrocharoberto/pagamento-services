package br.inatel.dm112.client;

import java.util.Date;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;

public class OrderRestClientUpdate {

	public static void main(String[] args) {
		OrderRestClient client = new OrderRestClient();

		Order orderToUpdate = client.retrieveOrder(456);
		if (orderToUpdate == null) {
			System.out.println("Order not found: " + 1);
			return;
		}

		orderToUpdate.setIssueDate(new Date());
		orderToUpdate.setStatus(0);

		OrderResponse resposta = client.updateOrder(orderToUpdate);

		System.out.println("Status do update do pedido " + orderToUpdate.getNumber() + ":  " + resposta.getStatus());
	}

}
