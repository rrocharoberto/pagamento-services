package br.inatel.dm112.adapter.model;

import java.io.FileInputStream;
import java.io.IOException;

//classe que simula a geração de um boleto lendo um arquivo PDF
public class BilletBuilderSimulated {

	public static String fileName = "example/boleto.pdf";

	public static byte[] getSimulatedBillet() {
		byte[] bytes = readPDFFile();
		if (bytes != null) {
			System.out.println("Sucesso na geração do boleto.");
		}
		return bytes;
	}

	private static byte[] readPDFFile() {
		// lê o conteúdo do arquivo pdf simulado
		byte[] bytes = null;
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
