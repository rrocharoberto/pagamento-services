package br.inatel.dm112;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "br.inatel.client.*") })
public class PagamentoApp {

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApp.class, args);
	}
}