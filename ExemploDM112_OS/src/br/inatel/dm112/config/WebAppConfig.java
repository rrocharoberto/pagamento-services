package br.inatel.dm112.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.inatel.dm112.service.BilletService;
import br.inatel.dm112.service.MailService;

@Configuration
@EnableWebMvc
@ComponentScan("br.inatel.dm112")
@PropertySource({ "classpath:email.properties" })
public class WebAppConfig {

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(env.getProperty("mail.host"));
		mailSender.setPort(587);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
		props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
		props.put("mail.debug", env.getProperty("mail.debug"));

		return mailSender;
	}

	@Bean
	public BilletService billetService() {
		return new BilletService();
	}

	@Bean
	public MailService mailService() {
		return new MailService();
	}
}
