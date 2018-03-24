package br.inatel.dm112.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.inatel.dm112.model.entities.OrderEntity;
import util.HibernateUtil;

public class OrderDAO {

	EntityManager em = HibernateUtil.getEntityManager();

	public void insert(OrderEntity o) {
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}

	public OrderEntity getOrderById(int number) {
		
		System.out.println(em.getProperties());
		
		return em.find(OrderEntity.class, number);
	}

	public List<OrderEntity> getOrdersByCPF(String cpf) {

		String ql = "select o from OrderEntity o where o.cpf = :cpfFilter";
		TypedQuery<OrderEntity> q = em.createQuery(ql, OrderEntity.class);
		q.setParameter("cpfFilter", cpf);

		List<OrderEntity> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	public List<OrderEntity> getAllOrders() {

		String ql = "select o from OrderEntity o";
		TypedQuery<OrderEntity> q = em.createQuery(ql, OrderEntity.class);

		List<OrderEntity> orders = q.getResultList();
		printOrders(orders);
		return orders;
	}

	private void printOrders(List<OrderEntity> orders) {
		System.out.println("qtd de orders: " + orders.size());
		for (OrderEntity order : orders) {
			System.out.println(order);
		}
	}

	public void updateOrder(OrderEntity o) {
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}
}
