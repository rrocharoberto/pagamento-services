package br.inatel.dm112.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.inatel.dm112.model.dao.OrderDAO;
import br.inatel.dm112.services.OrderService;

@Configuration
@EnableWebMvc
@ComponentScan("br.inatel.dm112")
public class PedidoConfig {

	@Bean
	public OrderService orderService() {
		return new OrderService();
	}

	@Bean
	public OrderDAO orderDAO() {
		return new OrderDAO();
	}

	@Bean
	public EntityManager entityManager() {
		EntityManagerFactory emFactory = null;

		try {
			// Create the Factory
			emFactory = Persistence.createEntityManagerFactory("pagamentoPU");
		} catch (Throwable e) {
			// Make sure you log the exception , as it might be swallowed
			System.err.println("Error creating EntityManagerFactory ." + e);
			throw new ExceptionInInitializerError(e);
		}
		return emFactory.createEntityManager();
	}
}
