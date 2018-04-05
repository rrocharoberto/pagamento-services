package br.inatel.dm112.adapter;

import org.jrimum.bopepo.view.BoletoViewer;

import br.inatel.dm112.adapter.model.BilletBuilder;
import br.inatel.dm112.adapter.model.BilletContent;

public class BilletAdapter {

	public byte[] execute(BilletContent content) {

		BilletBuilder boleto = new BilletBuilder(content);
		BoletoViewer boletoViewer = new BoletoViewer(boleto.getBoleto());

		// File arquivoPdf = boletoViewer.getPdfAsFile("MeuBoleto.pdf");

		byte[] bytes = boletoViewer.getPdfAsByteArray();

		return bytes;
	}

}
