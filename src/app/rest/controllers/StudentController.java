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

import app.components.StudentComponent;
import app.entity.Student;

@Component
@Path("/students")
public class StudentController {
	
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);

	
	@Autowired
	StudentComponent studentComponent;
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Long addStudent(
			@FormParam("studentId") int studentId, 
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("gender") String gender,
			@FormParam("sexualOrientation") String sexualOrientation,
			@FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude)
	{
		return studentComponent.register(studentId, name, email, gender, sexualOrientation, latitude, longitude);
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Long updateStudent(
			@FormParam("studentId") int studentId, 
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("gender") String gender,
			@FormParam("sexualOrientation") String sexualOrientation,
			@FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude)
	{
		return studentComponent.update(studentId, name, email, gender, sexualOrientation, latitude, longitude);
	}
	
	@POST
	@Path("/updateStatus")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateStudentStatus(
		@FormParam("studentId") int studentId,
		@FormParam("isSingle") Boolean isSingle,
		@FormParam("partnerId") long partnerId) {
		return studentComponent.updateStatus(studentId, isSingle, partnerId);
	}
	
	@Path("/singleStudents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getSingleStudents() {
		return studentComponent.getSingleStudents();			
	}
	
	@Path("/takenStudents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getTakenStudents() {
		return studentComponent.getTakenStudents();			
	}
	
	@Path("/find")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student findStudent(
		@QueryParam("studentId") int studentId) {
		return studentComponent.findStudent(studentId);			
	}
}
