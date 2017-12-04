package lu.martinm.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/counter")
public class CounterRestService {

	private int counter = 1;

	@GET
	public Response getCounter() {
		return Response.status(200).entity(counter).build();
	}

	@POST
	public Response postCounter(@PathParam("counterValue") int value) {
		return Response.status(200).entity(counter).build();
	}

	@PUT
	public Response putCounter(@PathParam("counterValue") int value) {
		return Response.status(200).entity(counter).build();
	}
	
	@DELETE
	public Response deleteCounter(@PathParam("id") int value) {
		return Response.status(200).entity("counter deleted").build();
	}

}
