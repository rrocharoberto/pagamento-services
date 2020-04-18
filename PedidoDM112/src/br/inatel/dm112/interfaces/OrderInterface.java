package br.inatel.dm112.interfaces;

import java.util.List;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;

public interface OrderInterface {

	public Order getOrder(int orderNumber);

	public Orders searchOrders(String cpf);

	public OrderResponse updateOrder(Order order);

	public List<Order> getAllOrders();
}