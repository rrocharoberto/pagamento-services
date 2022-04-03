package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Billet;
import br.inatel.dm112.model.BilletGenResponse;
import br.inatel.dm112.service.BilletService;

@RestController
@RequestMapping("/api")
public class BilletRest implements Billet {

	@Autowired
	private BilletService service;

	@Override
	//https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
	@GetMapping(value = "/billet/{orderNumber}/{cpf:.+}")
	@ResponseStatus(HttpStatus.OK)
	public BilletGenResponse generateBillet(
									@PathVariable("orderNumber") String orderNumber, 
									@PathVariable("cpf") String cpf) {
		
		System.out.println("BilletRest - generateBillet - cpf: " + cpf + " order: " + orderNumber);
		return service.generateBillet(orderNumber, cpf);
	}
}