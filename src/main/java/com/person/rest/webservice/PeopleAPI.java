package com.person.rest.webservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public People getTrackInJSON() {

		People track = new People();
		track.setId(1234);
		track.setName("Jose");
		track.setAge(5);
		track.setDob("03-30-2013");

		return track;

	}
	
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
			if(service.validate(p))
				ppl = service.create(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppl;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public People getPeople(@PathParam("id") int id) {
		People ppl = new People();
		try {
			    if(service.validateID(id)) {
					ppl = service.getPeople(id);
			    } /*else {
					return Response.status(400).entity(ppl).build();
				}*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ppl;

	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public People modifyPeople(@PathParam("id") int id, People p) {
		try {
			if(service.validateID(id)) {
				if(service.validate(p))
					p = service.updatePeople(id, p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
		
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removePeople(@PathParam("id") int id) {
		try {
			if(service.validateID(id)) {
				int record = service.removePeople(id);
				if(record == 0)
					return Response.status(400).entity("The record isn't removed").build();
				else
					return Response.status(200).entity("The record is successfully removed").build();
			} else {
				return Response.status(400).entity("The ID is not valid").build();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(id).build();
	}
	
	
}
