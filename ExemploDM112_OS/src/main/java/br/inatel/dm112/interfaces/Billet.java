package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.BilletGenResponse;

public interface Billet {

	public BilletGenResponse generateBillet(String orderNumber, String cpf);

}