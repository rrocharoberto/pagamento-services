package br.inatel.dm112.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.inatel.dm112.adapter.MailAdapter;
import br.inatel.dm112.adapter.TwilioMailAdapter;
import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.rest.support.UtilityException;

@Component
public class MailService {
	
	@Value("${email.fromPassword}")
	private String emailFromPassword;
	
	@Value("${twilio.sendgrid.api.key}")
	private String sendgridAPIKey;
	
	@Autowired
	MailAdapter sender;

	public void sendMail(MailRequestData mailData) {
		if (mailData.getFrom() == null || 
				mailData.getTo() == null || 
				mailData.getContent() == null) {
			throw new UtilityException("Null values not allowed in MailRequestData.");
		}

		//set credentials
		mailData.setPassword(emailFromPassword);
		mailData.setSendgridAPIKey(sendgridAPIKey);
		
		try {
			sender.sendMail(mailData);
		} catch(Exception e) {
			e.printStackTrace();
			throw new UtilityException("Error sending email: " + e.getMessage());
		}
		System.out.println("Email sent success.");
	}
}
