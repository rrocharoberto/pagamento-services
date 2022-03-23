package br.inatel.dm112.interfaces;

import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.MailStatusResponse;

public interface Email {

	public MailStatusResponse sendMail(MailRequestData mailData);

}