package br.inatel.dm112.client;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;

@SpringBootApplication
@ComponentScan(basePackages = "br.inatel.dm112")
public class OrderRestClientUpdate implements CommandLineRunner {

	@Autowired
	private OrderRestClient client;

	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderRestClientUpdate.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		int orderNumber = 456;

		Order orderToUpdate = client.retrieveOrder(orderNumber);
		if (orderToUpdate == null) {
			System.out.println("Order not found: " + 1);
			return;
		}

		// update the values for the order
		orderToUpdate.setIssueDate(new Date());
		orderToUpdate.setStatus(0);

		OrderResponse resposta = client.updateOrder(orderToUpdate);

		System.out.println("Status of updating the order " + resposta.getNumber() + ":  " + resposta.getStatus());
	}

}
