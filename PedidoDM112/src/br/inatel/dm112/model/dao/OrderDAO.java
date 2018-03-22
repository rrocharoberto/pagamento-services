package br.inatel.dm112.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.inatel.dm112.model.entities.Order;
import util.HibernateUtil;

public class OrderDAO {

	EntityManager em = HibernateUtil.getEntityManager();

	public void insert(Order o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	public Order getOrderById(int number) {
		return em.find(Order.class, number);
	}

	public List<Order> getOrdersByCPF(String cpf) {

		String ql = "select o from Order o where o.cpf = :cpfFilter";
		TypedQuery<Order> q = em.createQuery(ql, Order.class);
		q.setParameter("cpfFilter", cpf);

		List<Order> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	public List<Order> getAllOrders() {

		String ql = "select o from Order o";
		TypedQuery<Order> q = em.createQuery(ql, Order.class);

		List<Order> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	private void printOrders(List<Order> orders) {
		System.out.println("qtd de orders: " + orders.size());
		for (Order order : orders) {
			System.out.println(order);
		}
	}

	public void updateOrder(Order o) {
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}
}
