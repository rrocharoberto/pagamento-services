package br.inatel.dm112.client;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import reactor.core.publisher.Mono;

public class OrderRestClient {

	// local
	private String restURL = "http://localhost:9090/PedidoDM112/api";
	
	// GAE:
	//private String restURL = "https://dm112project.appspot.com/api";
	
	private final String endpoint = "/orders";

	/**
	 * createOrder
	 * @param order
	 * @return
	 */
	public OrderResponse createOrder(Order order) {

		String url = restURL + endpoint;
		
		OrderResponse orderResponse = WebClient.create(url)
		        .post()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), Order.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(OrderResponse.class)
		        .log().blockFirst();

		System.out.println("Resultado do createOrder: " + orderResponse);

		return orderResponse;
	}
	
	/**
	 * getItems
	 * @param cpf
	 * @return
	 */
	public List<Order> getOrdersByCPF(String cpf) {
		String url = restURL + endpoint + "/" + cpf;
		
		return WebClient.create(url)
		        .get()
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .retrieve()
		        .bodyToFlux(Order.class)
		        .collectList()
		        .log()
		        .block();
	}
	
	/**
	 * retrieveOrder
	 * @param orderNumber
	 * @return
	 */
	public Order retrieveOrder(int orderNumber) {
		String url = restURL + endpoint + "/" + orderNumber;
		
		return WebClient.create(url)
		        .get()
		        .retrieve()
		        .bodyToFlux(Order.class)
		        .log()
		        .blockFirst();
	}
	
	/**
	 * updateOrder
	 * @param order
	 * @return
	 */
	public OrderResponse updateOrder(Order order) {

		String url = restURL + endpoint;
		
		OrderResponse orderResponse = WebClient.create(url)
		        .put()
		        .contentType(MediaType.APPLICATION_JSON)
		        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
		        .body(Mono.just(order), Order.class)
		        .accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToFlux(OrderResponse.class)
		        .log().blockFirst();

		System.out.println("Resultado do updateOrder: " + orderResponse);

		return orderResponse;
	}
}
