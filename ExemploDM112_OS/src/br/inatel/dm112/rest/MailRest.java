package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Email;
import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.MailStatusResponse;
import br.inatel.dm112.service.MailService;

@RestController
@RequestMapping(value = "/api")
public class MailRest implements Email {

	@Autowired
	private MailService service;

	@Override
	@PostMapping(value = "/sendMail")
	//@GetMapping(value = "/sendMail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public MailStatusResponse sendMail(@RequestBody MailRequestData mailData) {

		return service.sendMail(mailData);
	}

}