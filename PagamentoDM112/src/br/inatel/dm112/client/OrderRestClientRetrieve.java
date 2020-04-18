package br.inatel.dm112.client;

import java.util.List;

import br.inatel.dm112.model.Order;

public class OrderRestClientRetrieve {

	public static void main(String[] args) {
		OrderRestClient client = new OrderRestClient();

		Order orderResp = client.retrieveOrder(456);
		if (orderResp == null) {
			System.out.println("Order not found: " + 1);
		} else {
			System.out.println(orderResp);
		}

		List<Order> list = client.getItems("111.111.111-11");

		System.out.println("List of orders retrieved from REST service");
		for (Order order : list) {
			System.out.println(order);
		}
	}
}
