package br.inatel.dm112.client.runner;

import java.io.FileInputStream;
import java.io.IOException;

import br.inatel.dm112.client.EmailClient;

public class EmailClientRunner {

	public static void main(String[] args) {
		EmailClient client = new EmailClient();
		client.setRestURL(ClientUtil.getUtilityRestEmailURL());
		client.setSendToAddress(ClientUtil.getEmailSendToAddress());

		int orderNumber = 5555; //any test order number
		byte[] bytes = readPDFFile();
		if (bytes != null) {
			client.callSendMailService(orderNumber, bytes);
			System.out.println("Sucesso na chamada para envio do email.");
		}
	}

	private static byte[] readPDFFile() {
		// lê o conteúdo do arquivo pdf de teste
		byte[] bytes = null;
		String fileName = "boleto.pdf";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			byte[] buffer = new byte[1 * 1024 * 1024]; // 1 MB
			int size = fis.read(buffer);
			bytes = new byte[size];
			System.arraycopy(buffer, 0, bytes, 0, size);
		} catch (IOException e) {
			System.out.println("Error reading PDF file: " + fileName);
			e.printStackTrace();
		}
		return bytes;
	}
}
