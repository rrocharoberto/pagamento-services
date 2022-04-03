package br.inatel.dm112.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.OrderInterface;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.services.OrderService;

@RestController
@RequestMapping("/api")
public class OrderRest implements OrderInterface {

	@Autowired
	private OrderService service;

	@Override
	@GetMapping("/orders/{orderNumber}")
	@ResponseBody
	public Order getOrder(@PathVariable("orderNumber") Integer orderNumber) {

		System.out.println("OrderRest - getOrder: " + orderNumber);
		return OrderService.convertToOrder(service.getOrder(orderNumber));
	}

	@Override
	@GetMapping("/orders/customer/{cpf:.+}")
	public List<Order> searchOrders(@PathVariable("cpf") String cpf) {

		System.out.println("OrderRest - searchOrders");
		return service.searchOrdersByCPF(cpf).stream().map(OrderService::convertToOrder).collect(Collectors.toList());
	}

	@Override
	@PutMapping("/orders/{orderNumber}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOrder(@RequestBody Order order, @PathVariable("orderNumber") Integer orderNumber) {

		System.out.println("OrderRest - updateOrder " + orderNumber);
		service.updateOrder(order, orderNumber);
	}

	@PostMapping("/orders")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void saveNewOrder(@RequestBody Order newOrder) {

		System.out.println("OrderRest - saveNewOrder");
		service.createOrder(newOrder);
	}

	// usado para teste
	@Override
	@GetMapping("/orders")
	public List<Order> getAllOrders() {

		System.out.println("OrderRest - getAllOrders");
		return service.getAllOrders();
	}
}
