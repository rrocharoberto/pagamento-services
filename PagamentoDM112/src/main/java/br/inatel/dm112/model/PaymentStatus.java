package br.inatel.dm112.model;

public class PaymentStatus {

	public enum PAY_STATUS {
		OK, NULL_VALUES, ORDER_NOT_FOUND, WRONG_ORDER_STATUR, ORDER_ERROR, BILLET_ERROR, EMAIL_ERROR
	}

	private String cpf;
	private int orderNumber;
	private int status;

	public PaymentStatus() {
	}

	public PaymentStatus(int status, String cpf, int orderNumber) {
		super();
		this.status = status;
		this.cpf = cpf;
		this.orderNumber = orderNumber;
	}

	public static PaymentStatus createErrorStatus(String cpf, int orderNumber, PAY_STATUS errorStatus) {
		return new PaymentStatus(errorStatus.ordinal(), cpf, orderNumber);
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

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "PaymentStatus [cpf=" + cpf + ", orderNumber=" + orderNumber + ", status=" + status + "]";
	}

}
