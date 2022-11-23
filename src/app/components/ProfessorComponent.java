package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Professor;
import app.entity.Student;
import app.repositories.ProfessorRepository;

@Component
public class ProfessorComponent {

	@Autowired
	private ProfessorRepository professorRepo;
	
	public Long register(int employeeId, String department, String name, String email, String gender, String sexualOrientation, double latitude, double longitude) {
		Professor newProfessor = new Professor();
		
		newProfessor.setEmployeeId(employeeId);
		newProfessor.setDepartment(department);
		newProfessor.setName(name);
		newProfessor.setEmail(email);
		newProfessor.setGender(gender);
		newProfessor.setSexualOrientation(sexualOrientation);
		newProfessor.setLatitude(latitude);
		newProfessor.setLongitude(longitude);
		
		newProfessor = professorRepo.save(newProfessor);
		return newProfessor.getId();
	}
	
	public Long update(int employeeId, String department, String name, String email, long partnerId, String gender, String sexualOrientation, double latitude, double longitude) {
		Professor oldProfessor = professorRepo.findByEmployeeId(employeeId);
		if (oldProfessor == null) {
			Professor newProfessor = new Professor();
			
			newProfessor.setEmployeeId(employeeId);
			newProfessor.setDepartment(department);
			newProfessor.setName(name);
			newProfessor.setEmail(email);
			newProfessor.setGender(gender);
			newProfessor.setSexualOrientation(sexualOrientation);
			newProfessor.setLatitude(latitude);
			newProfessor.setLongitude(longitude);
			
			newProfessor = professorRepo.save(newProfessor);
			return newProfessor.getId();
		} else {
			oldProfessor.setEmployeeId(employeeId);
			oldProfessor.setDepartment(department);
			oldProfessor.setName(name);
			oldProfessor.setEmail(email);
			oldProfessor.setPartnerId(partnerId);
			oldProfessor.setGender(gender);
			oldProfessor.setSexualOrientation(sexualOrientation);
			oldProfessor.setLatitude(latitude);
			oldProfessor.setLongitude(longitude);
			
			professorRepo.deleteById(oldProfessor.getId());
			oldProfessor = professorRepo.save(oldProfessor);
			return oldProfessor.getId();
		}
	}
	
	public String updateStatus(int employeeId, Boolean isSingle, long partnerId) {
		Professor oldProfessor = professorRepo.findByEmployeeId(employeeId);
		if (oldProfessor == null) {
			return "Professor does not exist in database.";
		} else {
			oldProfessor.setIsSingle(isSingle);
			oldProfessor.setPartnerId(partnerId);
			professorRepo.deleteById(oldProfessor.getId());
			oldProfessor = professorRepo.save(oldProfessor);
			return "Professor with id: " + String.valueOf(employeeId) + " has been updated.";
		}
	}
	
	public List<Professor> getSingleProfessors() {
		return professorRepo.findByIsSingle(false);
	}
	
	public List<Professor> getTakenProfessors() {
		return professorRepo.findByIsSingle(true);
	}
	
	public Professor findProfessor(int employeeId) {
		return professorRepo.findByEmployeeId(employeeId);
	}
}
