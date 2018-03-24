package br.inatel.dm112.client;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.namespace.QName;

import br.inatel.dm112.client.mail.stub.MailImpl;
import br.inatel.dm112.client.mail.stub.MailService;
import br.inatel.dm112.client.mail.stub.MailStatusResponse;

public class EmailClient {

	//TODO: modificar este email para enviar para outro endereço
	private static String sendTo = "rrocha.roberto@gmail.com";
	
	public MailStatusResponse callSendMailService(String from, String password, String to, byte[] content) {
		QName SERVICE_NAME = new QName("dm112", "mailService");

		//local:  http://localhost:8080/UtilityDM112/soap/mailservices?wsdl

		MailService service = new MailService(MailService.WSDL_LOCATION, SERVICE_NAME);
		MailImpl port = service.getMailImplPort();

		MailStatusResponse result = port.sendMail(from, password, to, content);
		return result;
	}

	public static void main(String[] args) {
		try {
			// le o conteúdo do arquivo pdf de teste
			FileInputStream fis = new FileInputStream("boleto.pdf");
			byte[] buffer = new byte[1 * 1024 * 1024]; // 1 MB
			int size = fis.read(buffer);
			byte[] bytes = new byte[size];
			System.arraycopy(buffer, 0, bytes, 0, size);
			fis.close();

			EmailClient client = new EmailClient();
			
			MailStatusResponse result = client.callSendMailService(
					"robertorr9@gmail.com", "robertodm112", sendTo, bytes);
			
			System.out.println("Resposta do email: " + result.getStatus());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
