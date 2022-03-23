package br.inatel.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.services.PaymentService;

@SpringBootApplication
@ComponentScan(basePackages = "br.inatel.dm112")
public class PaymentClient implements CommandLineRunner {

	@Autowired
	private PaymentService service;

	public static void main(String[] args) {
		new SpringApplicationBuilder(PaymentClient.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		PaymentStatus result = service.startPaymentOfOrder("111.111.111-11", 456);
		System.out.println("Resultado de startPaymentOfOrder: " + result);
	}

}