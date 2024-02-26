package br.inatel.dm112.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.BilletGenResponse;

@Service
public class BilletClient {
	@Value("${utility.rest.billet.url}")
	private String restURL;
	
	private String billetEndpoint = "/billet";
	
	public BilletGenResponse callGenerateBilletService(int orderNumber, String cpf) {

		String url = restURL + billetEndpoint + "/" + orderNumber + "/" + cpf;
		System.out.println("URL: " + url);
		
		return WebClient.create(url)
		        .get()
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(BilletGenResponse.class)
		        .log()
		        .blockFirst();
	}

	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}
}
