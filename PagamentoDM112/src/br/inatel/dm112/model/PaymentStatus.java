package br.inatel.dm112.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "dm112", name = "paymentStatus")
public class PaymentStatus {

	private String cpf;
	private String orderNumber;
	private int status;

	public PaymentStatus() {

	}

	public PaymentStatus(int status, String cpf, String orderNumber) {
		super();
		this.status = status;
		this.cpf = cpf;
		this.orderNumber = orderNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}
