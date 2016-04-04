package br.inatel.dm112.interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.services.entity.MailStatusResponse;

@Path("/")
public interface Email {

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/sendMail")
	public MailStatusResponse sendMail(@QueryParam("from") String from, 
							@QueryParam("password") String password, 
							@QueryParam("to")String to, 
							@QueryParam("content")byte[] content);

}