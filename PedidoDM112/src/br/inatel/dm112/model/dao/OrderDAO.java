package br.inatel.dm112.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.inatel.dm112.model.entities.OrderEntity;

@Repository
public class OrderDAO {

	@Autowired
	private EntityManager entityManager;

	public void insert(OrderEntity o) {
		System.out.println("Salvando pedido: " + o.getNumber());

		entityManager.getTransaction().begin();
		entityManager.persist(o);
		entityManager.getTransaction().commit();
	}

	public OrderEntity getOrderById(int number) {
		System.out.println("Consultando pedido com número: " + number);

		return entityManager.find(OrderEntity.class, number);
	}

	public List<OrderEntity> getOrdersByCPF(String cpf) {
		System.out.println("Consultando pedidos do cpf: " + cpf);

		String ql = "select o from OrderEntity o where o.cpf = :cpfFilter";
		TypedQuery<OrderEntity> q = entityManager.createQuery(ql, OrderEntity.class);
		q.setParameter("cpfFilter", cpf);

		List<OrderEntity> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	public List<OrderEntity> getAllOrders() {
		System.out.println("Consultando todos os pedidos.");

		String ql = "select o from OrderEntity o";
		TypedQuery<OrderEntity> q = entityManager.createQuery(ql, OrderEntity.class);

		List<OrderEntity> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	private void printOrders(List<OrderEntity> orders) {
		System.out.println("Quantidade de pedidos: " + orders.size());

		for (OrderEntity order : orders) {
			System.out.println(order);
		}
	}

	public void updateOrder(OrderEntity o) {
		System.out.println("Atualizando pedido: " + o.getNumber());

		entityManager.getTransaction().begin();
		entityManager.merge(o);
		entityManager.getTransaction().commit();
	}
}
