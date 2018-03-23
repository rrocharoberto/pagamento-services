package br.inatel.dm112.client;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;

public class OrderClient {

	//local
	private String restURL = "http://localhost:8080/PedidoDM112/rest/";
//	GAE: private String restURL = "http://dm112exemplo1.appspot.com/rest/";
	
	public Order retrieveOrder(String orderNumber) {
		Client client = JerseyClientBuilder.createClient();

		WebTarget webTarget = client.target(restURL).path("order").path(orderNumber);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		// GET
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		Order order = response.readEntity(Order.class);
		return order;
	}

	public Orders searchOrders(String cpf) {
		Client client = JerseyClientBuilder.createClient();

		WebTarget webTarget = client.target(restURL).path("orders").path(cpf);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		// GET
		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		Orders orders = response.readEntity(Orders.class);
		return orders;
	}

	public OrderResponse updateOrder(Order order) {

		Client client = JerseyClientBuilder.createClient();
		
		WebTarget webTarget = client.target(restURL).path("updateOrder");
		
		Invocation.Builder invocationBuilder = webTarget.request();

		// POST
		Response response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));
		OrderResponse orderResp = response.readEntity(OrderResponse.class);

//		Order response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON), Order.class);
		return orderResp;
	}

	// https://jersey.java.net/documentation/latest/client.html
	public static void main(String[] args) {

		OrderClient client = new OrderClient();
		
		Order orderResp = client.retrieveOrder("1");
		System.out.println(orderResp);

		Orders orders = client.searchOrders("123");

		for (Order o : orders.getOrders()) {
			System.out.println(o);
		}

		Order order = new Order();
		order.setCpf("111.111.111-11");
		order.setNumber(456);
		order.setOrderDate(new Date());
		order.setStatus(0);
		order.setValue(500);

		OrderResponse resposta = client.updateOrder(order);

		System.out.println("Status do update do pedido " + order.getNumber() + ":  " + resposta.getStatus());
	}

}
