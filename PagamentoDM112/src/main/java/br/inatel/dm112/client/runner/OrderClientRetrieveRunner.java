package br.inatel.dm112.client.runner;

import java.util.List;

import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.Order;

public class OrderClientRetrieveRunner {

	public static void main(String[] args) {
		OrderClient client = new OrderClient();
		client.setRestURL(ClientUtil.getOrderRestURL());

		int orderNumber = 2;

		Order orderResp = client.retrieveOrder(orderNumber);
		if (orderResp == null) {
			System.out.println("Order not found: " + orderNumber);
		} else {
			System.out.println(orderResp);
		}

		List<Order> orders = client.getOrdersByCPF("222.222.222-22");

		System.out.println("List of orders retrieved from REST service: ");
		for (Order order : orders) {
			System.out.println(order);
		}
	}
}
