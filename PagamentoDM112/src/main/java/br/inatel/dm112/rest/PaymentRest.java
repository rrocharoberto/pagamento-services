package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Payment;
import br.inatel.dm112.model.PaymentStatus;
import br.inatel.dm112.services.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentRest implements Payment {

	@Autowired
	private PaymentService service;

	@Override
	@GetMapping("/startPaymentOfOrder/{cpf:.+}/{orderNumber}")
	public PaymentStatus startPaymentOfOrder(
			@PathVariable("cpf")String cpf, 
			@PathVariable("orderNumber") int orderNumber) {
		
		System.out.println("PaymentRest - startPaymentOfOrder");
		return service.startPaymentOfOrder(cpf, orderNumber);
	}

	@Override
	@GetMapping("/confirmPaymentOfOrder/{cpf:.+}/{orderNumber}")
	public PaymentStatus confirmPaymentOfOrder(
			@PathVariable("cpf") String cpf, 
			@PathVariable("orderNumber") int orderNumber) {
		
		System.out.println("PaymentRest - confirmPaymentOfOrder");
		return service.confirmPaymentOfOrder(cpf, orderNumber);
	}

}