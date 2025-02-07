package br.inatel.dm112.adapter;

public class MailData {

	private int orderNumber;
	private String from;
	private String password;
	private String sendgridAPIKey;
	private String to;
	private byte[] content;

	public MailData() {
	}

	public MailData(int orderNumber, String from, String password, String to, byte[] content) {
		this.orderNumber = orderNumber;
		this.from = from;
		this.password = password;
		this.to = to;
		this.content = content;
	}

	public int getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getSendgridAPIKey() {
		return sendgridAPIKey;
	}
	
	public void setSendgridAPIKey(String sendgridAPIKey) {
		this.sendgridAPIKey = sendgridAPIKey;
	}
	
	@Override
	public String toString() {
		return "MailData [orderNumber=" + orderNumber + ", from=" + from + ", to=" + to + "]";
	}

}
