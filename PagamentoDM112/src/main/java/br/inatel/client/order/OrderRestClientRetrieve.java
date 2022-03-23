package br.inatel.client.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import br.inatel.dm112.model.Order;

@SpringBootApplication
@ComponentScan(basePackages = "br.inatel.dm112")
public class OrderRestClientRetrieve implements CommandLineRunner {

	@Autowired
	private OrderRestClient client;

	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderRestClientRetrieve.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		int orderNumber = 456;

		Order orderResp = client.retrieveOrder(orderNumber);
		if (orderResp == null) {
			System.out.println("Order not found: " + orderNumber);
		} else {
			System.out.println(orderResp);
		}

		List<Order> orders = client.getOrdersByCPF("111.111.111-11");

		System.out.println("List of orders retrieved from REST service: ");
		for (Order order : orders) {
			System.out.println(order);
		}
	}
}
