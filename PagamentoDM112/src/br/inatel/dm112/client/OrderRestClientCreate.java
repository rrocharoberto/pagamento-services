package br.inatel.dm112.client;

import java.util.Date;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;

public class OrderRestClientCreate {

	public static void main(String[] args) {
		OrderRestClient client = new OrderRestClient();

		Order order = new Order();
		order.setCpf("111.111.111-11");
		order.setNumber(789);
		order.setOrderDate(new Date());
		order.setStatus(1);
		order.setValue(200);

		OrderResponse resposta = client.createOrder(order);

		System.out.println("Status do update do pedido " + order.getNumber() + ":  " + resposta.getStatus());
	}

}
