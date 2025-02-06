package br.inatel.dm112.adapter;

import java.util.Properties;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("MAILCHIMP")
public class ChimpMailAdapter  extends JavaNativeMailAdapter {

	@Override
	protected Properties getEmailHostProperties() {
		//https://mailchimp.com/developer/transactional/docs/smtp-integration/
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mandrillapp.com");
		props.put("mail.smtp.port", "465");
		return props;
	}
}