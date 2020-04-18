package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.PaymentStatus;

public interface Payment {

	PaymentStatus startPaymentOfOrder(String cpf, int orderNumber);

	PaymentStatus confirmPaymentOfOrder(String cpf, int orderNumber);

}