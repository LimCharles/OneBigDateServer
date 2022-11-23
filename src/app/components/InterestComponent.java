package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entity.Interest;
import app.repositories.InterestRepository;

@Component
public class InterestComponent {

	@Autowired
	private InterestRepository interestRepo;
	
	public Long register(String name) {
		Interest newInterest = new Interest();
		
		newInterest.setName(name);
		
		newInterest = interestRepo.save(newInterest);
		return newInterest.getId();
	}
	
	public String delete(String name) {
		Interest oldInterest = interestRepo.findByName(name);
		if (oldInterest == null) {
			return "Interest does not exist in database.";
		} else {
			interestRepo.deleteById(oldInterest.getId());
			return "Interest with name: " + name + " has been deleted.";
		}
	}
}
