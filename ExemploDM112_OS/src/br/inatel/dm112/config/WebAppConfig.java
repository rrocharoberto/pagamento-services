package br.inatel.dm112.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.inatel.dm112.service.BilletService;

@Configuration
@EnableWebMvc
@ComponentScan("br.inatel.dm112")
public class WebAppConfig {

	@Bean
	public BilletService billetService() {
		return new BilletService();
	}

}
