package br.inatel.dm112.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.OrderInterface;
import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.rest.support.OrderDuplicateException;
import br.inatel.dm112.rest.support.OrderNotFoundException;
import br.inatel.dm112.rest.support.OrderResponseError;
import br.inatel.dm112.services.OrderService;

//local: http://localhost:8080/rest/orders/123
//GAE:   http://dm112exemplo1.appspot.com/rest/orders/123

@RestController
@RequestMapping("/api")
public class OrderRest implements OrderInterface {

	@Autowired
	private OrderService orderService;

	@Override
	@GetMapping("/order/{orderNumber}")
	public Order getOrder(@PathVariable("orderNumber") int orderNumber) {

		System.out.println("OrderRest - getOrder");
		return orderService.getOrder(orderNumber);
	}

	@Override
	@GetMapping("/orders/{cpf:.+}")
	public List<Order> searchOrders(@PathVariable("cpf") String cpf) {

		System.out.println("OrderRest - searchOrders");
		return orderService.searchOrders(cpf);
	}

	@Override
	@PutMapping("/order")
	public OrderResponse updateOrder(@RequestBody Order order) {

		System.out.println("OrderRest - updateOrder");
		orderService.updateOrder(order);
		return new OrderResponse(ResponseStatus.OK.ordinal());
	}

	//usado para teste
	@GetMapping("/orders")
	public List<Order> getAllOrders() {

		System.out.println("OrderRest - getAllOrders");
		return orderService.getAllOrders();
	}

	@PostMapping("order")
	public OrderResponse saveNewOrder(@RequestBody Order newOrder) {

		System.out.println("OrderRest - saveNewOrder");
		orderService.createOrder(newOrder);
		return new OrderResponse(ResponseStatus.OK.ordinal());
	}
	
	@ExceptionHandler
	public ResponseEntity<OrderResponseError> handlerException(OrderNotFoundException ex) {
		
		ex.printStackTrace();

		OrderResponseError error = new OrderResponseError(); // create OrderResponseError
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<OrderResponseError> handlerException(OrderDuplicateException ex) {
		
		ex.printStackTrace();

		OrderResponseError error = new OrderResponseError(); // create OrderResponseError
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
}