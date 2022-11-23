package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.InterestCertificate;

@Repository
public interface InterestCertificateRepository extends JpaRepository<InterestCertificate, Long> {
	public InterestCertificate findByStudentIdAndInterestId(Long studentId, Long interestId);
	public InterestCertificate findByProfessorIdAndInterestId(Long professorId, Long interestId);
}
