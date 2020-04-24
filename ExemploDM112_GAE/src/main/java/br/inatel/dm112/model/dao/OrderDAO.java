package br.inatel.dm112.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.inatel.dm112.model.entities.OrderEntity;

public class OrderDAO {

	private static HashMap<Integer, OrderEntity> orders = new HashMap<Integer, OrderEntity>();

	static {
		OrderEntity order = new OrderEntity();
		order.setCpf("123");
		order.setNumber(123);
		order.setOrderDate(new Date());
		order.setStatus(1);
		order.setValue(123.45f);
		
		orders.put(order.getNumber(), order);

		order = new OrderEntity();
		order.setCpf("123");
		order.setNumber(234);
		order.setOrderDate(new Date());
		order.setStatus(0);
		order.setValue(234.56f);
		
		orders.put(order.getNumber(), order);
}
	public void insert(OrderEntity o) {
		orders.put(o.getNumber(), o);
	}

	public OrderEntity getOrderById(int number) {
		return orders.get(number);
	}

	public List<OrderEntity> getOrdersByCPF(String cpf) {
		List<OrderEntity> list = new ArrayList<OrderEntity>();

		for (OrderEntity order : orders.values()) {
			if (order.getCpf().equals(cpf)) {
				list.add(order);
			}
		}

		return list;
	}

	public void updateOrder(OrderEntity o) {
		orders.put(o.getNumber(), o);
	}

	public List<OrderEntity> getAllOrders() {
		return new ArrayList<>(orders.values());
	}
}
