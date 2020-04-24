package br.inatel.dm112.model;

public class MailStatusResponse {

	public static enum STATUS {OK, ERROR};
	
	private int status;
	private String from;
	private String to;
	private String message;

	public MailStatusResponse() {

	}

	public MailStatusResponse(int status, String from, String to, String message) {
		super();
		this.status = status;
		this.from = from;
		this.to = to;
		this.message = message;
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

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MailStatusResponse [status=" + status + ", from=" + from + ", to=" + to + ", message=" + message + "]";
	}
}
