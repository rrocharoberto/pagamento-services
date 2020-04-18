package br.inatel.dm112.model;

public class MailStatusResponse {

	public static enum STATUS {OK, ERROR};
	
	private int status;
	private String from;
	private String to;

	public MailStatusResponse() {

	}

	public MailStatusResponse(int status, String from, String to) {
		super();
		this.status = status;
		this.from = from;
		this.to = to;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
