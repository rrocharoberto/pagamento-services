package br.inatel.dm112.adapter;

import java.util.Properties;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("GMAIL")
public class GmailMailAdapter extends JavaNativeMailAdapter {

	@Override
	protected Properties getEmailHostProperties() {
		//https://support.google.com/a/answer/176600
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}
}
