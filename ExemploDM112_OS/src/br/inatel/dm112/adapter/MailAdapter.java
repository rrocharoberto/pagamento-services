package br.inatel.dm112.adapter;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;

public class MailAdapter {

	//@Autowired
	//private JavaMailSender mailSender;
	
	public void sendMail(final String from, final String password, String to, byte[] content) {

		System.out.println("Enviando email para: " + to);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

//	    mailSender.setUsername("my.gmail@gmail.com");
//	    mailSender.setPassword("password");

	    
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Boleto");

			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPartText = new MimeBodyPart(); // texto
			messageBodyPartText.setText("Boleto gerado pelo sistema de Vendas");
			multipart.addBodyPart(messageBodyPartText);

			BodyPart messageBodyPartAtt = new MimeBodyPart(); // anexo
			ByteArrayDataSource source = new ByteArrayDataSource(content, "application/pdf");
			source.setName("Boleto.pdf");

			messageBodyPartAtt.setDataHandler(new DataHandler(source));
			messageBodyPartAtt.setFileName("Boleto_Venda.pdf");
			multipart.addBodyPart(messageBodyPartAtt);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
