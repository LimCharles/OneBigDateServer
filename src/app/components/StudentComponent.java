package app.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Student;
import app.entity.Interest;
import app.entity.InterestCertificate;
import app.repositories.StudentRepository;
import app.repositories.InterestRepository;
import app.repositories.InterestCertificateRepository;

@Component
public class StudentComponent {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private InterestCertificateRepository interestCertRepo;
	
	@Autowired
	private InterestRepository interestRepo;
	
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
	
	public List<StudentMatchDTO> matchStudent(int studentId) {
		Student student = studentRepo.findByStudentId(studentId);
		Long id = student.getId();
		List<InterestCertificate> studentInterests = interestCertRepo.findByStudentId(id);
		double totalInterests = studentInterests.size();
		
		// prepare response
		List<StudentMatchDTO> studentMatches = new ArrayList<StudentMatchDTO>();
		
		// get all single students
		List<Student> allSingleStudents = studentRepo.findByIsSingle(false);
		System.out.println(allSingleStudents);
		
		for (Student s : allSingleStudents) {
			Long currId = s.getId();
			if (currId == student.getId()) {
				break;
			}

			// get student interests
			List<InterestCertificate> currentStudentInterests = interestCertRepo.findByStudentId(s.getId());
			
			// format student as StudentMatchDTO
			StudentMatchDTO studentMatch = new StudentMatchDTO();
			studentMatch.setStudentId(s.getStudentId());
			studentMatch.setName(s.getName());
			studentMatch.setEmail(s.getEmail());
			studentMatch.setIsSingle(s.getIsSingle());
			studentMatch.setPartnerId(s.getPartnerId());
			studentMatch.setGender(s.getGender());
			studentMatch.setSexualOrientation(s.getSexualOrientation());
			studentMatch.setLatitude(s.getLatitude());
			studentMatch.setLongitude(s.getLongitude());
			
			double match = 0;
			
			// loop through current student's interests & check if
			// given student has same interest
			// if so, increment match
			for (InterestCertificate ic : currentStudentInterests) {
				for (InterestCertificate sic : studentInterests) {
					// skip if current student is original student
					if (sic.getInterestId().equals(ic.getInterestId())) {
						match += 1;
						break;
					}
				}
			}
			double compatibility = (match/totalInterests) * 100;
			studentMatch.setCompatibility(compatibility);
			
			// filter out students with more than 0% compatibility
			if (compatibility > 0) {
				// compute for distance from student
				double longitude1 = Math.toRadians(student.getLongitude());
				double longitude2 = Math.toRadians(s.getLongitude());
				double latitude1 = Math.toRadians(student.getLatitude());
				double latitude2 = Math.toRadians(s.getLatitude());
				
				double difflon = longitude2 - longitude1;
				double difflat = latitude2 - latitude1;
				double a = Math.pow(Math.sin(difflat / 2), 2)
						+ Math.cos(latitude1) * Math.cos(latitude2)
						* Math.pow(Math.sin(difflon / 2),2);
					
				double c = 2 * Math.asin(Math.sqrt(a));

				double r = 6371;
				
				double distance = (c*r);
				
				// filter out students with distance more than 15km
				if (distance <= 15) {					
					studentMatches.add(studentMatch);
				}
			}
			
			System.out.println("MATCH REPORT ON " + s.getName());
			System.out.println("Total interests: " + totalInterests + "; Matches: " + match + "; Compatibility: " + compatibility);
		}
		
		// sort by decreasing compatibility
		Collections.reverse(studentMatches);
		
		return studentMatches;
	}
}
