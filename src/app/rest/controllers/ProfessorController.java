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

import app.components.ProfessorComponent;
import app.components.ProfessorMatchDTO;
import app.entity.Professor;
import app.entity.Student;

@Component
@Path("/professors")
public class ProfessorController {
	
	
	Logger logger = LoggerFactory.getLogger(ProfessorController.class);

	
	@Autowired
	ProfessorComponent professorComponent;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Long addProfessor(
			@FormParam("employeeId") int employeeId,
			@FormParam("department") String department,
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("gender") String gender,
			@FormParam("sexualOrientation") String sexualOrientation,
			@FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude)
	{
		return professorComponent.register(employeeId, department, name, email, gender, sexualOrientation, latitude, longitude);
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Long updateProfessor(
			@FormParam("employeeId") int employeeId,
			@FormParam("department") String department,
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("partnerId") long partnerId,
			@FormParam("gender") String gender,
			@FormParam("sexualOrientation") String sexualOrientation,
			@FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude)
	{
		return professorComponent.update(employeeId, department, name, email, partnerId, gender, sexualOrientation, latitude, longitude);
	}
	
	@POST
	@Path("/updateStatus")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateProfessorStatus(
		@FormParam("employeeId") int employeeId,
		@FormParam("isSingle") Boolean isSingle,
		@FormParam("partnerId") long partnerId) {
		return professorComponent.updateStatus(employeeId, isSingle, partnerId);
	}
	
	@Path("/singleProfessors")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getSingleProfessors() {
		return professorComponent.getSingleProfessors();			
	}
	
	@Path("/takenProfessors")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getTakenProfessors() {
		return professorComponent.getTakenProfessors();			
	}
	
	@Path("/find")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Professor findProfessor(
		@QueryParam("employeeId") int employeeId) {
		return professorComponent.findProfessor(employeeId);			
	}
	
	@Path("/match")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProfessorMatchDTO> matchStudent(
		@QueryParam("employeeId") int employeeId) {
			return professorComponent.matchProfessor(employeeId);
	}
}
