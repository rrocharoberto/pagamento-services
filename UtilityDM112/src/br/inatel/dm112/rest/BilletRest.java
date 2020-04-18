package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Billet;
import br.inatel.dm112.model.BilletGenResponse;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.service.BilletService;

@RestController
@RequestMapping("/api")
public class BilletRest implements Billet {

	@Autowired
	private BilletService service;

	@Override
	//https://stackoverflow.com/questions/16332092/spring-mvc-pathvariable-with-dot-is-getting-truncated
	@PostMapping(value = "/generateBillet/{orderNumber}/{cpf:.+}")
	public BilletGenResponse generateBillet(
									@PathVariable("orderNumber") String orderNumber, 
									@PathVariable("cpf") String cpf) {
		
		System.out.println("BilletRest - generateBillet");
		BilletGenResponse result = new BilletGenResponse();
		try {
			// gera o pdf e obtem os bytes
			byte[] pdfContent = service.generateBillet(orderNumber, cpf);

			// monta a resposta
			result.setMessage("Sucesso na geração do boleto - cpf: " + cpf + " pedido: " + orderNumber);
			result.setPdfContent(pdfContent);
			result.setStatus(ResponseStatus.OK.ordinal());
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(
					"Erro gerando o boleto - cpf: " + cpf + " pedido: " + orderNumber + " - " + e.getMessage());
			result.setStatus(ResponseStatus.ERROR.ordinal());
		}
		System.out.println("BilletRest - generateBillet - Resuls: " + result.getMessage());
		return result;
	}
}