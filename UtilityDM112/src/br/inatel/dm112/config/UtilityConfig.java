package br.inatel.dm112.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.inatel.dm112.service.BilletService;
import br.inatel.dm112.service.MailService;

@Configuration
@EnableWebMvc
@ComponentScan("br.inatel.dm112")
public class UtilityConfig {

	@Bean
	public BilletService billetService() {
		return new BilletService();
	}

	@Bean
	public MailService mailService() {
		return new MailService();
	}
}
