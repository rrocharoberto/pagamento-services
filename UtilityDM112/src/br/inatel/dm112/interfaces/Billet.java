package br.inatel.dm112.interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.inatel.dm112.services.entity.BilletGenResponse;

@Path("/")
public interface Billet {

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/generateBillet")
	public BilletGenResponse generateBillet(@QueryParam("orderNumber") String orderNumber, 
										@QueryParam("cpf") String cpf);

}