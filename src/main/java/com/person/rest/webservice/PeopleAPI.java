package com.person.rest.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/people")
public class PeopleAPI {
	PeopleService service = new PeopleService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<People> listPeople()  {
		List<People> allPeople = new ArrayList<People>();
		try {
			allPeople = service.getAllPeople();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allPeople;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public People createPeople(People p) {
		People ppl = new People();
		try {
			ppl = service.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppl;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public People getPeople(@PathParam("id") String id) {
		People ppl = new People();
		try {
			ppl = service.getPeople(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ppl;

	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public People modifyPeople(@PathParam("id") String id, People p) {
		try {
			p = service.updatePeople(id, p);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
		

	}
	
	@DELETE
	@Path("/{id}")
	public Response removePeople(@PathParam("id") String id) {
		try {
			service.removePeople(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(id).build();
	}
	
	
}