package com.person.rest.webservcie;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;


import com.person.rest.webservice.People;
import com.person.rest.webservice.PeopleService;


public class PersonWebserviceTest {
	  
	public void testGetAllPeople() {
		try {
			PeopleService ppl = new PeopleService();
			List<People> all = ppl.getAllPeople();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("");
		}
	}
	   
	 

}
