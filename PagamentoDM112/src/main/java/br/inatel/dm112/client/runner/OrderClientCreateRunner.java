package br.inatel.dm112.client.runner;

import java.util.Date;

import br.inatel.dm112.client.OrderClient;
import br.inatel.dm112.model.Order;

public class OrderClientCreateRunner {

	public static void main(String[] args) {
		OrderClient client = new OrderClient();
		client.setRestURL(ClientUtil.getOrderRestURL());

		String cpf = "111.111.111-11";
		
		Order order = new Order();
		order.setCpf(cpf);
		order.setOrderDate(new Date());
		order.setStatus(1);
		order.setValue(200);

		client.createOrder(order);
	}
}
