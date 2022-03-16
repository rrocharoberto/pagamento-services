package br.inatel.dm112.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.OrderEntity;
import br.inatel.dm112.rest.support.OrderDuplicateException;
import br.inatel.dm112.rest.support.OrderNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public Order getOrder(int orderNumber) {
		
		OrderEntity entity = orderDAO.getOrderById(orderNumber);
		Order order = null;
		if (entity != null) {
			order = convertToOrder(entity);
		}
		return order;
	}

	public List<Order> searchOrders(String cpf) {
		
		List<OrderEntity> entities = orderDAO.getOrdersByCPF(cpf);
		List<Order> orders = new ArrayList<Order>();
		for (OrderEntity entity : entities) {
			Order order = convertToOrder(entity);
			orders.add(order);
		}
		return orders;
	}

	public void updateOrder(Order order) {

		OrderEntity entity = orderDAO.getOrderById(order.getNumber());
		if (entity == null) {
			throw new OrderNotFoundException(
					"Pedido não encontrado para fazer update: cpf: " + order.getCpf() + "valor: " + order.getValue());
		}
		convertOrderToEntityWithoutPK(order, entity); //don't change PK
		orderDAO.updateOrder(entity);
		System.out.println("OrderImpl updateOrder - atualizou o pedido com número: " + order.getNumber());
	}

	public void createOrder(Order order) {

		OrderEntity entity = orderDAO.getOrderById(order.getNumber());
		if (entity != null) {
			throw new OrderDuplicateException("Pedido já existe: " + order.getNumber());//TODO: melhorar a semântica
		}
		entity = new OrderEntity();
		entity.setNumber(order.getNumber());
		convertOrderToEntityWithoutPK(order, entity);

		System.out.println("OrderImpl updateOrder - pedido não encontrado com número: " + order.getNumber());
		orderDAO.insert(entity);
	}

	public List<Order> getAllOrders() {
		List<OrderEntity> entities = orderDAO.getAllOrders();
		List<Order> orders = new ArrayList<>();

		for (OrderEntity entity : entities) {
			Order order = convertToOrder(entity);
			orders.add(order);
		}
		return orders;
	}

	private Order convertToOrder(OrderEntity entity) {
		Order order = new Order(
				entity.getNumber(), 
				entity.getCpf(), 
				entity.getValue(), 
				entity.getStatus(), 
				entity.getOrderDate(),
				entity.getIssueDate(), 
				entity.getPaymentDate());
		return order;
	}

	private void convertOrderToEntityWithoutPK(Order order, OrderEntity entity) {
		entity.setCpf(order.getCpf());
		entity.setValue(order.getValue());
		entity.setStatus(order.getStatus());
		entity.setOrderDate(order.getOrderDate());
		entity.setIssueDate(order.getIssueDate());
		entity.setPaymentDate(order.getPaymentDate());
	}
	
}