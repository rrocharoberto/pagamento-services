
package br.inatel.dm112.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.Order;

//local: http://localhost:8080/rest/orders/123
//GAE:   http://dm112exemplo1.appspot.com/rest/orders/123

@Path("/")
public class OrderImplREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/order/{orderNumber}")
	public Order getOrder(@PathParam("orderNumber") int orderNumber) {
		return new OrderDAO().getOrderById(orderNumber);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/orders/{cpf}")
	public Orders searchOrders(@PathParam("cpf") String cpf) {
		Orders orders = new Orders();
		orders.setOrders(new OrderDAO().getOrdersByCPF(cpf));
		return orders;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateOrder")
	public OrderResponse updateOrder(Order order) {
		OrderDAO dao = new OrderDAO();
		
		Order persisted = dao.getOrderById(order.getNumber());
		if (persisted != null) {
			dao.updateOrder(order);
			return new OrderResponse(ResponseStatus.OK.ordinal());
		} else {
			System.out.println("OrderImpl updateOrder - order não encontrado com número: " + order.getNumber());
			dao.insert(order);
			return new OrderResponse(ResponseStatus.ERROR.ordinal());
		}
	}

}
