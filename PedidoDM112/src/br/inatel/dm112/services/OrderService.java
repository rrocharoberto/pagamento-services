package br.inatel.dm112.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.OrderEntity;
import br.inatel.dm112.rest.support.OrderNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	public Order getOrder(int orderNumber) {
		OrderEntity en = orderDAO.getOrderById(orderNumber);
		Order order = null;
		if (en != null) {
			order = new Order(en.getNumber(), en.getCpf(), en.getValue(), en.getStatus(), en.getOrderDate(),
					en.getIssueDate(), en.getPaymentDate());
		}
		return order;
	}

	public Orders searchOrders(String cpf) {
		List<OrderEntity> entities = orderDAO.getOrdersByCPF(cpf);
		Orders orders = new Orders();
		for (OrderEntity en : entities) {
			Order order = new Order(en.getNumber(), en.getCpf(), en.getValue(), en.getStatus(), en.getOrderDate(),
					en.getIssueDate(), en.getPaymentDate());
			orders.addOrder(order);
		}
		return orders;
	}

	public void updateOrder(Order order) {

		OrderEntity entity = orderDAO.getOrderById(order.getNumber());
		if (entity != null) {
			// entity.setNumber(order.getNumber()); //don't change the PK
			entity.setCpf(order.getCpf());
			entity.setValue(order.getValue());
			entity.setStatus(order.getStatus());
			entity.setOrderDate(order.getOrderDate());
			entity.setIssueDate(order.getIssueDate());
			entity.setPaymentDate(order.getPaymentDate());
			orderDAO.updateOrder(entity);
			System.out.println("OrderImpl updateOrder - atualizou a order com número: " + order.getNumber());
		} else {
			throw new OrderNotFoundException(
					"Pedido não encontrado para fazer update: cpf: " + order.getCpf() + "valor: " + order.getValue());
		}
	}

	public void createOrder(Order order) {

		OrderEntity entity = orderDAO.getOrderById(order.getNumber());
		if (entity == null) {
			entity = new OrderEntity();
			entity.setNumber(order.getNumber());
			entity.setCpf(order.getCpf());
			entity.setValue(order.getValue());
			entity.setStatus(order.getStatus());
			entity.setOrderDate(order.getOrderDate());
			entity.setIssueDate(order.getIssueDate());
			entity.setPaymentDate(order.getPaymentDate());

			System.out.println("OrderImpl updateOrder - order não encontrado com número: " + order.getNumber());
			orderDAO.insert(entity);
		} else {
			throw new OrderNotFoundException("Pedido já existe: " + order.getNumber());
		}
	}

	public List<Order> getAllOrders() {
		List<OrderEntity> entities = orderDAO.getAllOrders();
		List<Order> orders = new ArrayList<>();

		for (OrderEntity entity : entities) {
			Order order = new Order();
			order.setNumber(entity.getNumber());
			order.setCpf(entity.getCpf());
			order.setValue(entity.getValue());
			order.setStatus(entity.getStatus());
			order.setOrderDate(entity.getOrderDate());
			order.setIssueDate(entity.getIssueDate());
			order.setPaymentDate(entity.getPaymentDate());
			orders.add(order);
		}
		return orders;
	}
}