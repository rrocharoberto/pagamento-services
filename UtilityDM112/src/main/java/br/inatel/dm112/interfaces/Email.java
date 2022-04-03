package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.MailRequestData;

public interface Email {

	public void sendMail(MailRequestData mailData);

}