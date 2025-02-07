package br.inatel.dm112.adapter;

import java.io.IOException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
@Profile("TWILIO")
public class TwilioMailAdapter implements MailAdapter {

	public void sendMail(MailData mailData) {

		System.out.println("Enviando email do pedido " + mailData.getOrderNumber() + " para: " + mailData.getTo());

		String subject = "Boleto do pedido " + mailData.getOrderNumber();
		String mailContent = "Boleto do pedido " + mailData.getOrderNumber() + " gerado pelo E-comerce DM112.";

		Email from = new Email(mailData.getFrom());
		Email to = new Email(mailData.getTo());

		Content content = new Content("text/plain", mailContent);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(mailData.getSendgridAPIKey());
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(
					"Sendgrid response data: statusCode: " + response.getStatusCode() + " body: " + response.getBody());
			System.out.println("Email sent successfully....");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
