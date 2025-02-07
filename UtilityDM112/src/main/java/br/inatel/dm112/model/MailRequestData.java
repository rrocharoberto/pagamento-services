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
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "MailData [orderNumber=" + orderNumber + ", to=" + to + "]";
	}

}
