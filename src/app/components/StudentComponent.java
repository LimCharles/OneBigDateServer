package app.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Student;
import app.repositories.StudentRepository;

@Component
public class StudentComponent {

	@Autowired
	private StudentRepository studentRepo;
	
	public Long register(int studentId, String name, String email, String gender, String sexualOrientation, double latitude, double longitude) {
		Student newStudent = new Student();
		
		newStudent.setStudentId(studentId);
		newStudent.setName(name);
		newStudent.setEmail(email);
		newStudent.setGender(gender);
		newStudent.setSexualOrientation(sexualOrientation);
		newStudent.setLatitude(latitude);
		newStudent.setLongitude(longitude);
		
		newStudent = studentRepo.save(newStudent);
		return newStudent.getId();
	}
	
	public Long update(int studentId, String name, String email, String gender, String sexualOrientation, double latitude, double longitude) {
		Student oldStudent = studentRepo.findByStudentId(studentId);
		if (oldStudent == null) {
			Student newStudent = new Student();
			
			newStudent.setStudentId(studentId);
			newStudent.setName(name);
			newStudent.setEmail(email);
			newStudent.setGender(gender);
			newStudent.setSexualOrientation(sexualOrientation);
			newStudent.setLatitude(latitude);
			newStudent.setLongitude(longitude);
			
			newStudent = studentRepo.save(newStudent);
			return newStudent.getId();
		} else {
			oldStudent.setStudentId(studentId);
			oldStudent.setName(name);
			oldStudent.setEmail(email);
			oldStudent.setGender(gender);
			oldStudent.setSexualOrientation(sexualOrientation);
			oldStudent.setLatitude(latitude);
			oldStudent.setLongitude(longitude);

			studentRepo.deleteById(oldStudent.getId());
			oldStudent = studentRepo.save(oldStudent);
			return oldStudent.getId();
		}
	}
	
	public String updateStatus(int studentId, Boolean isSingle, long partnerId) {
		Student oldStudent = studentRepo.findByStudentId(studentId);
		if (oldStudent == null) {
			return "Student does not exist in database.";
		} else {
			oldStudent.setIsSingle(isSingle);
			oldStudent.setPartnerId(partnerId);
			studentRepo.deleteById(oldStudent.getId());
			oldStudent = studentRepo.save(oldStudent);
			return "Student with id: " + String.valueOf(studentId) + " has been updated.";
		}
	}
	
	public List<Student> getSingleStudents() {
		return studentRepo.findByIsSingle(false);
	}
	
	public List<Student> getTakenStudents() {
		return studentRepo.findByIsSingle(true);
	}
	
	public Student findStudent(int studentId) {
		return studentRepo.findByStudentId(studentId);
	}
}
