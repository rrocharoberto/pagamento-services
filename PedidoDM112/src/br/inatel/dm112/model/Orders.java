package br.inatel.dm112.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.inatel.dm112.model.entities.Order;

@XmlRootElement(name = "orderList")
public class Orders {

	private List<Order> orders = new ArrayList<Order>();

	@XmlElement(name = "orders")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
