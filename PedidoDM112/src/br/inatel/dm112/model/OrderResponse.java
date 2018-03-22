package br.inatel.dm112.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orderResponse")
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
}
