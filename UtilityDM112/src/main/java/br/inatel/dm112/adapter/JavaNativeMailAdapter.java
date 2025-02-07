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

public abstract class JavaNativeMailAdapter implements MailAdapter {

	public void sendMail(MailData mailData) {

		System.out.println("Enviando email do pedido " + mailData.getOrderNumber() + " para: " + mailData.getTo());

		Properties props = getEmailHostProperties();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailData.getFrom(), mailData.getPassword());
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailData.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailData.getTo()));
			message.setSubject("Boleto do pedido " + mailData.getOrderNumber());

			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPartText = new MimeBodyPart(); // texto
			messageBodyPartText.setText("Boleto do pedido " + mailData.getOrderNumber() + " gerado pelo E-comerce DM112.");
			multipart.addBodyPart(messageBodyPartText);

			BodyPart messageBodyPartAtt = new MimeBodyPart(); // anexo
			ByteArrayDataSource source = new ByteArrayDataSource(mailData.getContent(), "application/pdf");
			source.setName("Boleto" + mailData.getOrderNumber() + ".pdf");

			messageBodyPartAtt.setDataHandler(new DataHandler(source));
			messageBodyPartAtt.setFileName("Boleto_Venda" + mailData.getOrderNumber() + ".pdf");
			multipart.addBodyPart(messageBodyPartAtt);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Email sent successfully....");
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected abstract Properties getEmailHostProperties();
}
