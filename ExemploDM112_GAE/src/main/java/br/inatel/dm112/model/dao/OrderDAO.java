package br.inatel.dm112.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.inatel.dm112.model.entities.Order;

public class OrderDAO {

	private static HashMap<Integer, Order> orders = new HashMap<Integer, Order>();

	static {
		Order order = new Order();
		order.setCpf("123");
		order.setNumber(123);
		order.setOrderDate(new Date());
		order.setStatus(1);
		order.setValue(123.45f);
		
		orders.put(order.getNumber(), order);

		order = new Order();
		order.setCpf("123");
		order.setNumber(234);
		order.setOrderDate(new Date());
		order.setStatus(0);
		order.setValue(234.56f);
		
		orders.put(order.getNumber(), order);
}
	public void insert(Order o) {
		orders.put(o.getNumber(), o);
	}

	public Order getOrderById(int number) {
		return orders.get(number);
	}

	public List<Order> getOrdersByCPF(String cpf) {
		List<Order> list = new ArrayList<Order>();

		for (Order order : orders.values()) {
			if (order.getCpf().equals(cpf)) {
				list.add(order);
			}
		}

		return list;
	}

	public void updateOrder(Order o) {
		orders.put(o.getNumber(), o);
	}
}
