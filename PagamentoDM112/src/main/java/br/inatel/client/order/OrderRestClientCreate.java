package br.inatel.client.order;

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
public class OrderRestClientCreate implements CommandLineRunner {

	public static int orderNumber = 789;
	public static String cpf = "111.111.111-11";

	@Autowired
	private OrderRestClient client;

	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderRestClientCreate.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(String... args) throws Exception {

		Order order = new Order();
		order.setCpf(cpf);
		order.setNumber(orderNumber);
		order.setOrderDate(new Date());
		order.setStatus(1);
		order.setValue(200);

		OrderResponse resposta = client.createOrder(order);

		System.out.println("Status of creating the order " + resposta.getNumber() + ":  " + resposta.getStatus());
	}

}
