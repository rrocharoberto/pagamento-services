package br.inatel.dm112.adapter;

import br.inatel.dm112.model.MailRequestData;

public interface MailAdapter {
	public void sendMail(MailRequestData mailData);
}
