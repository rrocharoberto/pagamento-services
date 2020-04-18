package br.inatel.dm112.model;

import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "orderList")
public class Orders {

	private List<Order> orders = new ArrayList<Order>();

//	@XmlElement(name = "orders")
	public List<Order> getOrders() {
		return orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
