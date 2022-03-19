package br.inatel.dm112.client;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.BilletGenResponse;

@SpringBootApplication
@ComponentScan(basePackages = "br.inatel.dm112")
public class BilletClient implements CommandLineRunner {

	@Value("${utility.rest.url}")
	private String restURL;

	public static void main(String[] args) {
		new SpringApplicationBuilder(BilletClient.class).web(WebApplicationType.NONE).run(args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		BilletClient client = new BilletClient();
		BilletGenResponse result = client.callGenerateBilletService(123, "111.111.111-11");
		System.out.println(result.getMessage());

		String fileName = "boleto.pdf";
		if (result.getStatus() == 0) { // OK
			// escreve o arquivo pdf de teste
			try (FileOutputStream fos = new FileOutputStream(fileName)) {
				fos.write(result.getPdfContent());
				fos.flush();

			} catch (IOException e) {
				System.out.println("Error writing PDF file: " + fileName);
				e.printStackTrace();
			}
		}
	}
	
	public BilletGenResponse callGenerateBilletService(int orderNumber, String cpf) {

		String url = restURL + "/generateBillet/" + orderNumber + "/" + cpf;
		
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
}
