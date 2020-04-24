package br.inatel.dm112.model;

public class OrderResponse {

	private int status;

	public OrderResponse() {

	}

	public OrderResponse(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "OrderResponse [status=" + status + "]";
	}
	
}
