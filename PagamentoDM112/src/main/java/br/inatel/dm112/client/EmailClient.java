package br.inatel.dm112.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.MailRequestData;
import reactor.core.publisher.Mono;

@Service
public class EmailClient {

	@Value("${utility.rest.url}")
	private String restURL;

	@Value("${email.sendFromAddress}")
	private String sendFromAddress;

	@Value("${email.sendToAddress}")
	private String sendToAddress;
	
	@Value("${email.password}")
	private String sendPassAddress;

	private String mailEndpoint = "/mail";
	
	public void callSendMailService(byte[] content) {

		String url = restURL + mailEndpoint ;
		System.out.println("URL: " + url);
		
		MailRequestData mrd = new MailRequestData(sendFromAddress, sendPassAddress, sendToAddress, content);
		
		WebClient
				.create(url)
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(mrd), MailRequestData.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToMono(String.class).defaultIfEmpty("")
		        .log()
		        .block();
	}
	
	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}
	
	public void setSendFromAddress(String sendFromAddress) {
		this.sendFromAddress = sendFromAddress;
	}
	
	public void setSendToAddress(String sendToAddress) {
		this.sendToAddress = sendToAddress;
	}

	public void setSendPassAddress(String sendPassAddress) {
		this.sendPassAddress = sendPassAddress;
	}
}
