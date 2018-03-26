package br.inatel.dm112.client;

import java.io.FileOutputStream;
import java.io.IOException;

import br.inatel.dm112.client.billet.stub.BilletGenResponse;
import br.inatel.dm112.client.billet.stub.BilletImpl;
import br.inatel.dm112.client.billet.stub.BilletService;

public class BilletClient {

	public BilletGenResponse callGenerateBilletService(String orderNumber, String cpf) {

		// local:
		// http://localhost:8080/UtilityDM112/soap/billetservices?wsdl

		BilletService service = new BilletService();
		BilletImpl port = service.getBilletImplPort();
		BilletGenResponse result = port.generateBillet(orderNumber, cpf);
		return result;
	}

	public static void main(String[] args) {
		try {
			BilletClient client = new BilletClient();
			BilletGenResponse result = client.callGenerateBilletService("123", "111.111.111-11");
			System.out.println(result.getMessage());
			if (result.getStatus() == 0) { // OK
				// escreve o arquivo pdf de teste
				FileOutputStream fos = new FileOutputStream("boleto.pdf");
				fos.write(result.getPdfContent());
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
