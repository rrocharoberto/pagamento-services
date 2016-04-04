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

//		String ql = "select o from Order o where o.cpf = :cpfFilter";
		String ql = "select o from Order o";
		TypedQuery<Order> q = em.createQuery(ql, Order.class);
//		q.setParameter("cpfFilter", cpf);

		List<Order> orders = q.getResultList();
		System.out.println("qtd de orders do cpf " + cpf + " : " + orders.size());
		for (Order order : orders) {
			System.out.println(order);
		}
		return orders;
	}
	
	public void updateOrder(Order o) {
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
	}
}
