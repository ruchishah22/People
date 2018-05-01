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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

@Path("/people")
public class PeopleAPI {
	PeopleService service = new PeopleService();
	ResponseBuilder builder = null;
	Error error = null;
	@Context
    private UriInfo uri;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPeople() throws  Exception {
		List<People> allPeople = new ArrayList<People>();
		
			allPeople = service.getAllPeople();		
		    return Response.status(Response.Status.OK).entity(allPeople).type(MediaType.APPLICATION_JSON).build();
			 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPeople(People p) throws Exception{
		People ppl = new People();		
		try {
			error = service.validate(p);
			if(error.getStatusCode() != 420) {
				ppl = service.create(p);
				return Response.status(Response.Status.OK).entity(ppl).type(MediaType.APPLICATION_JSON).build();
			} else {	
				error = new Error(error.getStatusCode(),error.getStatusMessage(),uri.getRequestUri().toString()) ;		
			    return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			error = new Error(400,e.getMessage(), uri.getRequestUri().toString()) ;
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPeople(@PathParam("id") int id) throws Exception{
		People ppl = new People();
		
		
		try {
			    if(service.validateID(id)) {
					ppl = service.getPeople(id);
					return Response.status(Response.Status.OK).entity(ppl).type(MediaType.APPLICATION_JSON).build();
			    } else {
			    		error = new Error(404,"Person with id " + id + " not found",uri.getRequestUri().toString()) ;	    		
			    		return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
			    }
		}catch(Exception e) {
			error = new Error(400, e.getMessage(), uri.getRequestUri().toString()) ;
			
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyPeople(@PathParam("id") int id, People p) throws Exception {
		
		try {
			if(service.validateID(id)) {
				error = service.validate(p);
				if(error.getStatusCode() != 422) {
					p = service.updatePeople(id, p);
					return Response.status(Response.Status.OK).entity(p).type(MediaType.APPLICATION_JSON).build();
				} else {
					error = new Error(error.getStatusCode(),error.getStatusMessage(),uri.getRequestUri().toString()) ;		
				    return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
				}
			}else {
				error = new Error(404,"Person with id " + id + " not found",uri.getRequestUri().toString()) ;		
			    return Response.status(Response.Status.NOT_FOUND).entity(error).type(MediaType.APPLICATION_JSON).build();
			
			}
		}catch(Exception e) {
			e.printStackTrace();
			error = new Error(400,e.getMessage(), uri.getRequestUri().toString()) ;
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removePeople(@PathParam("id") int id) throws Exception{
		try {
			if(service.validateID(id)) {
				int record = service.removePeople(id);
				if(record == 0) {
					error = new Error(400,"Requested Entity is not removed",uri.getRequestUri().toString()) ;			
				} else {
					return Response.status(Response.Status.OK).entity("Requested Entity is successfully removed").type(MediaType.APPLICATION_JSON).build();
				}
			} else {
				error = new Error(400,"Person with id " + id + " not found",uri.getRequestUri().toString()) ;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(error).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	
	
}
