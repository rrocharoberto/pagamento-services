package br.inatel.dm112.adapter;

import java.io.FileInputStream;
import java.io.IOException;

import br.inatel.dm112.adapter.model.BilletContent;

public class BilletAdapter {

	public byte[] execute(BilletContent content) {

		//Estava chamando o BilletBuilder quando o Jrimun estava no ar
		//O BilletBuilder foi removido nesta versão.
		//Por enquanto não usaremos o parâmetro BilletContent
		//
		//BilletBuilder boleto = new BilletBuilder(content);
		//BoletoViewer boletoViewer = new BoletoViewer(boleto.getBoleto());
		//byte[] bytes = boletoViewer.getPdfAsByteArray();

		
		//Agora está lendo um pdf fixo simulado.
		byte[] bytes = readPDFFile();
		if (bytes != null) {
			System.out.println("Sucesso na geração do boleto.");
		}

		return bytes;
	}
	
	private static byte[] readPDFFile() {
		// lê o conteúdo do arquivo pdf simulado
		byte[] bytes = null;
		String fileName = "example/boleto.pdf";
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
