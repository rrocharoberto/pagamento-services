package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;

public interface Order {

	public br.inatel.dm112.model.entities.Order getOrder(int orderNumber);

	public Orders searchOrders(String cpf);

	public OrderResponse updateOrder(br.inatel.dm112.model.entities.Order order);

}