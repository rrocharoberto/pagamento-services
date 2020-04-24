package br.inatel.dm112.client;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.BilletGenResponse;

public class BilletClient {

	// local
	private String restURL = "http://localhost:8080/UtilityDM112/api/";
	//OpenShift
	//private String restURL = "http://dm112-dm112project.apps.ca-central-1.starter.openshift-online.com/ExemploDM112_OS/api/";

	public BilletGenResponse callGenerateBilletService(int orderNumber, String cpf) {

		String url = restURL + "generateBillet/" + orderNumber + "/" + cpf;
		
		return WebClient
				.create(url)
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(BilletGenResponse.class)
		        .log()
		        .blockFirst();
	}

	public static void main(String[] args) {
		try {
			BilletClient client = new BilletClient();
			BilletGenResponse result = client.callGenerateBilletService(123, "111.111.111-11");
			System.out.println(result.getMessage());
			if (result.getStatus() == 0) { // OK
				// escreve o arquivo pdf de teste
				FileOutputStream fos = new FileOutputStream("boleto.pdf");
				fos.write(result.getPdfContent());
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
