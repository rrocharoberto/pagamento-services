package br.inatel.dm112.config;

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
}
