package br.inatel.dm112.client;

import java.util.List;

import br.inatel.dm112.model.Order;

public class OrderRestClientRetrieve {

	public static void main(String[] args) {
		OrderRestClient client = new OrderRestClient();

		int orderNumber = 456;
		
		Order orderResp = client.retrieveOrder(orderNumber);
		if (orderResp == null) {
			System.out.println("Order not found: " + orderNumber);
		} else {
			System.out.println(orderResp);
		}

		List<Order> orders = client.getOrdersByCPF("111.111.111-11");

		System.out.println("List of orders retrieved from REST service");
		for (Order order : orders) {
			System.out.println(order);
		}
	}
}
