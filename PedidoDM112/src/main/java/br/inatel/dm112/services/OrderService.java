package br.inatel.dm112.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.dao.OrderRepository;
import br.inatel.dm112.model.entities.OrderEntity;
import br.inatel.dm112.rest.support.InvalidOrderOperationException;
import br.inatel.dm112.rest.support.OrderNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public OrderEntity getOrder(int orderNumber) {

		Optional<OrderEntity> obj = repo.findById(orderNumber);
		return obj.orElseThrow(() -> new OrderNotFoundException("Order " + orderNumber + " not found."));
	}

	public List<OrderEntity> searchOrdersByCPF(String cpf) {

		List<OrderEntity> list = repo.findByCPF(cpf);
		return list;
	}

	public void updateOrder(Order order, Integer orderNumber) {

		OrderEntity entity = getOrder(orderNumber);
		updateOrderData(order, entity); // don't change PK
		repo.save(entity);
		System.out.println("OrderImpl updateOrder - atualizou o pedido com número: " + order.getNumber());
	}
	
	public void startPayment(int orderNumber) {
		OrderEntity entity = getOrder(orderNumber);
		if(entity.getStatus() != Order.STATUS.PENDING.ordinal()) {
			throw new InvalidOrderOperationException("Order is not PENDING. Status: " + entity.getStatus());
		}
		entity.setIssueDate(new Date());
		entity.setStatus(Order.STATUS.PENDING.ordinal());
		repo.save(entity);
		System.out.println("OrderImpl confirmPayment - confirmou o pagamento do pedido com número: " + orderNumber);
	}

	public void confirmPayment(int orderNumber) {
		OrderEntity entity = getOrder(orderNumber);
		entity.setPaymentDate(new Date());
		entity.setStatus(Order.STATUS.CONFIRMED.ordinal());
		repo.save(entity);
		System.out.println("OrderImpl confirmPayment - confirmou o pagamento do pedido com número: " + orderNumber);
	}

	public OrderEntity createOrder(Order order) {

		OrderEntity entity = convertToEntity(order);
		repo.save(entity);
		System.out.println("OrderImpl createOrder - pedido criado com número: " + entity.getNumber());
		return entity;
	}

	public List<Order> getAllOrders() {
		List<OrderEntity> entities = repo.findAll();
		List<Order> orders = new ArrayList<>();

		for (OrderEntity entity : entities) {
			Order order = convertToOrder(entity);
			orders.add(order);
		}
		return orders;
	}

	private void updateOrderData(Order order, OrderEntity entity) {
		entity.setCPF(order.getCpf());
		entity.setValue(order.getValue());
		entity.setStatus(order.getStatus());
		entity.setOrderDate(order.getOrderDate());
		entity.setIssueDate(order.getIssueDate());
		entity.setPaymentDate(order.getPaymentDate());
	}

	public static Order convertToOrder(OrderEntity entity) {
		Order order = new Order(entity.getNumber(), entity.getCPF(), entity.getValue(), entity.getStatus(),
				entity.getOrderDate(), entity.getIssueDate(), entity.getPaymentDate());
		return order;
	}

	public static OrderEntity convertToEntity(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.setCPF(order.getCpf());
		entity.setValue(order.getValue());
		entity.setStatus(order.getStatus());
		entity.setOrderDate(order.getOrderDate());
		entity.setIssueDate(order.getIssueDate());
		entity.setPaymentDate(order.getPaymentDate());
		return entity;
	}

}