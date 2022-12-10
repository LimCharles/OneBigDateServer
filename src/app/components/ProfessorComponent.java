package app.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Professor;
import app.entity.InterestCertificate;
import app.repositories.ProfessorRepository;
import app.repositories.InterestCertificateRepository;

@Component
public class ProfessorComponent {

	@Autowired
	private ProfessorRepository professorRepo;
	
	@Autowired
	private InterestCertificateRepository interestCertRepo;
	
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
	
	public List<ProfessorMatchDTO> matchProfessor(int employeeId) {
		Professor prof = professorRepo.findByEmployeeId(employeeId);
		Long id = prof.getId();
		List<InterestCertificate> profInterests = interestCertRepo.findByProfessorId(id);
		double totalInterests = profInterests.size();
		
		// prepare response
		List<ProfessorMatchDTO> professorMatches = new ArrayList<ProfessorMatchDTO>();
		
		// get all single professors
		List<Professor> allSingleProfessors = professorRepo.findByIsSingle(false);
		
		for (Professor p : allSingleProfessors) {
			if (p.getId() == prof.getId()) {
				break;
			}
			
			// get professor interests
			List<InterestCertificate> currentProfInterests = interestCertRepo.findByProfessorId(p.getId());
			
			// format professor as ProfessorMatchDTO
			ProfessorMatchDTO profMatch = new ProfessorMatchDTO();
			profMatch.setEmployeeId(p.getEmployeeId());
			profMatch.setDepartment(p.getDepartment());
			profMatch.setName(p.getName());
			profMatch.setEmail(p.getEmail());
			profMatch.setIsSingle(p.getIsSingle());
			profMatch.setPartnerId(p.getPartnerId());
			profMatch.setGender(p.getGender());
			profMatch.setSexualOrientation(p.getSexualOrientation());
			profMatch.setLatitude(p.getLatitude());
			profMatch.setLongitude(p.getLongitude());
			
			double match = 0;
			
			// loop through current prof's interests & check if
			// given prof has same interest
			// if so, increment match
			for (InterestCertificate ic : currentProfInterests) {
				for (InterestCertificate pic : profInterests) {
					if (pic.getInterestId() == ic.getInterestId()) {
						match += 1;
						break;
					}
				}
			}
			
			double compatibility = (match/totalInterests) * 100;
			profMatch.setCompatibility(compatibility);
			
			// filter out profs with less than 0% compatibility
			if (compatibility > 0) {
				// compute for distance from prof
				double longitude1 = Math.toRadians(prof.getLongitude());
				double longitude2 = Math.toRadians(p.getLongitude());
				double latitude1 = Math.toRadians(prof.getLatitude());
				double latitude2 = Math.toRadians(p.getLatitude());
				
				double difflon = longitude2 - longitude1;
				double difflat = latitude2 - latitude1;
				double a = Math.pow(Math.sin(difflat / 2), 2)
						+ Math.cos(latitude1) * Math.cos(latitude2)
						* Math.pow(Math.sin(difflon / 2),2);
					
				double c = 2 * Math.asin(Math.sqrt(a));

				double r = 6371;
				
				double distance = (c*r);
				
				// filter out profs with distance > 15km
				if (distance <= 15) {
					professorMatches.add(profMatch);
				}
			}
			
			System.out.println("MATCH REPORT ON " + p.getName());
			System.out.println("Total interests: " + totalInterests + "; Matches: " + match + "; Compatibility: " + compatibility);
		}
		
		// sort by decreasing compatibility
		Collections.reverse(professorMatches);
		
		return professorMatches;
	}
}
