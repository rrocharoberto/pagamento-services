package br.inatel.dm112.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.model.Order;
import br.inatel.dm112.model.OrderResponse;
import br.inatel.dm112.model.Orders;
import br.inatel.dm112.model.ResponseStatus;
import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.model.entities.OrderEntity;

@Path("/")
public class OrderImpl implements br.inatel.dm112.interfaces.OrderInterface {

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/order/{orderNumber}")
	public Order getOrder(@PathParam("orderNumber") int orderNumber) {
		OrderEntity en = new OrderDAO().getOrderById(orderNumber);
		Order order = null;
		if(en != null) {
			order = new Order(en.getNumber(), en.getCpf(), en.getValue(), en.getStatus(), en.getOrderDate(), en.getIssueDate(), en.getPaymentDate());
		}
		return order;
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/orders/{cpf}")
	public Orders searchOrders(@PathParam("cpf") String cpf) {
		List<OrderEntity> entities = new OrderDAO().getOrdersByCPF(cpf);
		Orders orders = new Orders();
		for (OrderEntity en : entities) {
			Order order = new Order(en.getNumber(), en.getCpf(), en.getValue(), en.getStatus(), en.getOrderDate(), en.getIssueDate(), en.getPaymentDate());
			orders.addOrder(order);
		}
		return orders;
	}

	@Override
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateOrder")
	public OrderResponse updateOrder(Order order) {
		OrderDAO dao = new OrderDAO();
		
		OrderEntity entity = dao.getOrderById(order.getNumber());
		if (entity != null) {
			dao.updateOrder(entity);
			return new OrderResponse(ResponseStatus.OK.ordinal());
		} else {
			entity = new OrderEntity();
			entity.setNumber(order.getNumber());
			entity.setCpf(order.getCpf());
			entity.setValue(order.getValue());
			entity.setStatus(order.getStatus());
			entity.setOrderDate(order.getOrderDate());
			entity.setIssueDate(order.getIssueDate());
			entity.setPaymentDate(order.getPaymentDate());
			
			System.out.println("OrderImpl updateOrder - order não encontrado com número: " + order.getNumber());
			dao.insert(entity);
			return new OrderResponse(ResponseStatus.ERROR.ordinal());
		}
	}

}