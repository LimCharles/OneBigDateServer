package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.*;
import app.repositories.*;

@Component
public class InterestCertificateComponent {

	@Autowired
	private InterestCertificateRepository interestCertificateRepo;
	
	@Autowired
	private InterestRepository interestRepo;

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	public String registerStudentInterest(String interestName, int studentId) {
		Interest studentInterest = interestRepo.findByName(interestName);
		Student registeree = studentRepo.findByStudentId(studentId);
		
		if (studentInterest == null) {
			return "Interest does not exist.";
		}
		if (registeree == null ) {
			return "Student does not exist.";
		}
		
		InterestCertificate newInterestCertificate = new InterestCertificate();
		newInterestCertificate.setStudentId(registeree.getId());
		newInterestCertificate.setInterestId(studentInterest.getId());
		
		newInterestCertificate = interestCertificateRepo.save(newInterestCertificate);
		return "Student Interest has successfully been registered";
	}
	
	public String registerProfessorInterest(String interestName, int employeeId) {
		Interest professorInterest = interestRepo.findByName(interestName);
		Professor registeree = professorRepo.findByEmployeeId(employeeId);
		
		if (professorInterest == null) {
			return "Interest does not exist.";
		}
		if (registeree == null ) {
			return "Professor does not exist.";
		}
		
		InterestCertificate newInterestCertificate = new InterestCertificate();
		newInterestCertificate.setProfessorId(registeree.getId());
		newInterestCertificate.setInterestId(professorInterest.getId());
		
		newInterestCertificate = interestCertificateRepo.save(newInterestCertificate);
		return "Professor Interest has successfully been registered";
	}
	
	public String deleteStudentInterest(String interestName, int studentId) {
		Interest studentInterest = interestRepo.findByName(interestName);
		Student registeree = studentRepo.findByStudentId(studentId);
		
		if (studentInterest == null) {
			return "Interest does not exist.";
		}
		if (registeree == null ) {
			return "Student does not exist.";
		}
		
		InterestCertificate oldInterestCertificate = interestCertificateRepo.findByStudentIdAndInterestId(registeree.getId(), studentInterest.getId());
		interestCertificateRepo.deleteById(oldInterestCertificate.getId());
		return "Interest has been removed from student";
	}
	
	public String deleteProfessorInterest(String interestName, int employeeId) {
		Interest professorInterest = interestRepo.findByName(interestName);
		Professor registeree = professorRepo.findByEmployeeId(employeeId);
		
		if (professorInterest == null) {
			return "Interest does not exist.";
		}
		if (registeree == null ) {
			return "Professor does not exist.";
		}
		
		InterestCertificate oldInterestCertificate = interestCertificateRepo.findByStudentIdAndInterestId(registeree.getId(), professorInterest.getId());
		interestCertificateRepo.deleteById(oldInterestCertificate.getId());
		return "Interest has been removed from professor";
	}
	
}
