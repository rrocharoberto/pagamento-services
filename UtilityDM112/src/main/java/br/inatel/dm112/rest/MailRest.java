package br.inatel.dm112.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.dm112.interfaces.Email;
import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.service.MailService;

@RestController
@RequestMapping(value = "/api")
public class MailRest implements Email {

	@Autowired
	private MailService service;

	@Override
	@PostMapping(value = "/mail")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendMail(@RequestBody MailRequestData mailData) {

		System.out.println("MailRest - sendMail to: " + mailData.getTo());
		service.sendMail(mailData);
	}

}