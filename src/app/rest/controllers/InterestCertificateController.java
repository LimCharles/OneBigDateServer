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

import app.components.InterestCertificateComponent;
import app.entity.InterestCertificate;

@Component
@Path("/interestRegistration")
public class InterestCertificateController {
	
	
	Logger logger = LoggerFactory.getLogger(InterestCertificateController.class);

	
	@Autowired
	InterestCertificateComponent interestCertificateComponent;
	
	@POST
	@Path("/registerStudentInterest")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addStudentInterest(
			@FormParam("interestName") String interestName,
			@FormParam("studentId") int studentId)
	{
		return interestCertificateComponent.registerStudentInterest(interestName, studentId);
	}
	
	@POST
	@Path("/registerProfessorInterest")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addProfessorInterest(
			@FormParam("interestName") String interestName,
			@FormParam("employeeId") int employeeId)
	{
		return interestCertificateComponent.registerProfessorInterest(interestName, employeeId);
	}

	@POST
	@Path("/deleteStudentInterest")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteStudentInterest(
			@FormParam("interestName") String interestName,
			@FormParam("studentId") int studentId)
	{
		return interestCertificateComponent.deleteStudentInterest(interestName, studentId);
	}
	
	@POST
	@Path("/deleteProfessorInterest")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteProfessorInterest(
			@FormParam("interestName") String interestName,
			@FormParam("employeeId") int employeeId)
	{
		return interestCertificateComponent.deleteProfessorInterest(interestName, employeeId);
	}
}
