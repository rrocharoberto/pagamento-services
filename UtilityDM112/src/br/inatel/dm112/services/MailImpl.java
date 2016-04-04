package br.inatel.dm112.services;

import javax.jws.WebService;

import br.inatel.dm112.adapter.MailAdapter;
import br.inatel.dm112.interfaces.Email;
import br.inatel.dm112.services.entity.MailStatusResponse;

@WebService(targetNamespace = "dm112", serviceName = "mailService")
public class MailImpl implements Email {

	@Override
	public MailStatusResponse sendMail(String from, String password, String to, byte[] content) {
		if (from == null || password == null || to == null || content == null) {
			return new MailStatusResponse(MailStatusResponse.STATUS.ERROR.ordinal(), from, to);
		}

		MailAdapter sender = new MailAdapter();
		sender.sendMail(from, password, to, content);
		return new MailStatusResponse(MailStatusResponse.STATUS.OK.ordinal(), from, to);
	}

}