package br.inatel.dm112.client.runner;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import br.inatel.dm112.client.BilletClient;
import br.inatel.dm112.model.BilletGenResponse;

@Service
public class BilletClientRunner {

	public static void main(String[] args) {
		BilletClient client = new BilletClient();
		client.setRestURL(ClientUtil.getUtilityRestBilletURL());

		String fileName = "boleto.pdf";
		int orderNumber = 1;
		String cpf = "111.111.111-11";

		try {
			BilletGenResponse result = client.callGenerateBilletService(orderNumber, cpf);

			// escreve o arquivo pdf de teste
			try (FileOutputStream fos = new FileOutputStream(fileName)) {
				fos.write(result.getPdfContent());
				fos.flush();

			} catch (IOException e) {
				System.out.println("Error writing PDF file: " + fileName);
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erro no serviço de boleto");
		}
	}
}
