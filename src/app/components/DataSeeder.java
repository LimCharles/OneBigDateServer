package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.*;
import app.repositories.*;

@Component
public class DataSeeder {
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	@Autowired
	private InterestRepository interestRepo;
	
	@PostConstruct
	public void init() {
		if (studentRepo.count()==0) {		
			seedStudent(202927, "Michael Angelo", "michaelangelo@gmail.com", "male", "cis", 0.00, 0.00);
			seedStudent(202583, "Steven Eagle", "steveneagle@gmail.com", "male", "gay", 0.00, 0.00);
			seedStudent(202489, "Kim Lim", "kimlim@gmail.com", "male", "gay", 0.00, 0.00);
			seedStudent(202623, "Donald Rump", "donaldrump@gmail.com", "male", "pan", 0.00, 0.00);
			seedStudent(202391, "Obama Barack", "obamabarack@gmail.com", "male", "cis", 0.00, 0.00);
			seedStudent(202940, "Angelica Jolie", "angelicaj@gmail.com", "female", "cis", 0.00, 0.00);
			seedStudent(202427, "Cruise Cruz", "couscous@gmail.com", "male", "pan", 0.00, 0.00);
		}	
		if (professorRepo.count()==0) {		
			seedProfessor(123894, "SOSE", "John Richard", "johnrichard@gmail.com", "male", "bi", 0.00, 0.00);
			seedProfessor(446712, "SOSE", "Captain Hook", "chook@gmail.com", "male", "cis", 0.00, 0.00);
			seedProfessor(525892, "SOSE", "Peter Spatula", "peterspat@gmail.com", "non-binary", "pan", 0.00, 0.00);
			seedProfessor(534582, "SOSE", "Sumting Won", "sumtingw@gmail.com", "male", "pan", 0.00, 0.00);
			seedProfessor(535278, "SOSE", "Angela Mallari", "angelam@gmail.com", "female", "pan", 0.00, 0.00);
			seedProfessor(567239, "SOSE", "Samantha Cruz", "samcruz@gmail.com", "female", "cis", 0.00, 0.00);
			seedProfessor(572082, "SOSE", "Ciara Lim", "ciaral@gmail.com", "female", "cis", 0.00, 0.00);
		}	
		if (interestRepo.count()==0) {
			seedInterest("Cooking");
			seedInterest("Sci-Fi");
			seedInterest("Video Games");
			seedInterest("Smoking");
			seedInterest("Cats");
			seedInterest("Dogs");
			seedInterest("Tutle Watching");
		}
	}
	
	private void seedStudent(int studentId, String name, String email, String gender, String sexualOrientation, double latitude, double longitude) {
		Student s = new Student();
		s.setStudentId(studentId);
		s.setName(name);
		s.setEmail(email);
		s.setGender(gender);
		s.setSexualOrientation(sexualOrientation);
		s.setLatitude(latitude);
		s.setLongitude(longitude);
		studentRepo.save(s);
	}

	private void seedProfessor(int employeeId, String department, String name, String email, String gender, String sexualOrientation, double latitude, double longitude) {
		Professor p = new Professor();
		p.setEmployeeId(employeeId);
		p.setDepartment(department);
		p.setName(name);
		p.setEmail(email);
		p.setGender(gender);
		p.setSexualOrientation(sexualOrientation);
		p.setLatitude(latitude);
		p.setLongitude(longitude);
		professorRepo.save(p);
	}

	private void seedInterest(String interestName) {
		Interest i = new Interest();
		i.setName(interestName);
		interestRepo.save(i);
	}

}
