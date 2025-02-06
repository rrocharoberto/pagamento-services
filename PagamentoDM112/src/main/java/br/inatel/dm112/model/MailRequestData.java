package br.inatel.dm112.model;

public class MailRequestData {

	private int orderNumber;
	private String to;
	private byte[] content;

	public MailRequestData() {
	}

	public MailRequestData(int orderNumber, String to, byte[] content) {
		this.orderNumber = orderNumber;
		this.to = to;
		this.content = content;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public String getTo() {
		return to;
	}

	public byte[] getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "MailRequestData [orderNumber=" + orderNumber + ", to=" + to + "]";
	}

}
