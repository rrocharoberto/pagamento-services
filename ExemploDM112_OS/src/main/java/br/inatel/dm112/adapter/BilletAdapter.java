package br.inatel.dm112.adapter;

import org.jrimum.bopepo.view.BoletoViewer;

import br.inatel.dm112.adapter.model.BilletBuilder;
import br.inatel.dm112.adapter.model.BilletBuilderSimulated;
import br.inatel.dm112.adapter.model.BilletContent;

public class BilletAdapter {

	public byte[] execute(BilletContent content) {

		//O BilletBuilder usa o Jrimun para gerar um boleto de exemplo
		BilletBuilder boleto = new BilletBuilder(content);
		BoletoViewer boletoViewer = new BoletoViewer(boleto.getBoleto());
		return boletoViewer.getPdfAsByteArray();

		//Caso ocorrer erro na biblioteca Jrimun, podemos ler um PFD fixo simulado.
		//return BilletBuilderSimulated.getSimulatedBillet();
	}
}
