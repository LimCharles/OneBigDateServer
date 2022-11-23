package app.rest.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.InterestComponent;
import app.entity.Interest;

@Component
@Path("/interests")
public class InterestController {
	
	
	Logger logger = LoggerFactory.getLogger(InterestController.class);

	
	@Autowired
	InterestComponent interestComponent;
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Long addInterest(
			@FormParam("name") String name)
	{
		return interestComponent.register(name);
	}
}
