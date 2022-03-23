package br.inatel.client.email;

import java.io.FileInputStream;
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

import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.MailStatusResponse;
import reactor.core.publisher.Mono;

@SpringBootApplication
@ComponentScan(basePackages = "br.inatel.dm112")
public class EmailClient implements CommandLineRunner {

	@Value("${utility.rest.url}")
	private String restURL;

	@Value("${email.sendToAddress}")
	private String sendToAddress;
	@Value("${email.sendFromAddress}")
	private String sendFromAddress;
	@Value("${email.password}")
	private String sendPassAddress;

	private String mailEndpoint = "/mail";
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(EmailClient.class).web(WebApplicationType.NONE).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		byte[] bytes = readPDFFile();
		if(bytes != null) {
			MailStatusResponse result = callSendMailService(
					sendFromAddress, sendPassAddress, sendToAddress, bytes);
			
			System.out.println("Resposta do email: " + result.getStatus());
		}
	}
	
	public MailStatusResponse callSendMailService(String from, String password, String to, byte[] content) {

		String url = restURL + mailEndpoint ;
		
		MailRequestData mrd = new MailRequestData(from, password, to, content);
		
		return WebClient
				.create(url)
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(mrd), MailRequestData.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(MailStatusResponse.class)
		        .log()
		        .blockFirst();
	}

	private byte[] readPDFFile() {
		// lê o conteúdo do arquivo pdf de teste
		byte[] bytes = null;
		String fileName = "boleto.pdf";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			byte[] buffer = new byte[1 * 1024 * 1024]; // 1 MB
			int size = fis.read(buffer);
			bytes = new byte[size];
			System.arraycopy(buffer, 0, bytes, 0, size);
		} catch (IOException e) {
			System.out.println("Error reading PDF file: " + fileName);
			e.printStackTrace();
		}
		return bytes;
	}
}
