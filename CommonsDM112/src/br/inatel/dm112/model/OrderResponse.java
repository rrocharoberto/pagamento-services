package br.inatel.dm112.model;

public class OrderResponse {

	private int number;

	private int status;

	public OrderResponse() {

	}

	public OrderResponse(int number, int status) {
		this.number = number;
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "OrderResponse [number=" + number + ", status=" + status + "]";
	}
}
