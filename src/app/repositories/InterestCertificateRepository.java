package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.InterestCertificate;

@Repository
public interface InterestCertificateRepository extends JpaRepository<InterestCertificate, Long> {
	public InterestCertificate findByStudentIdAndInterestId(Long studentId, Long interestId);
	public List<InterestCertificate> findByStudentId(Long studentId);
	public InterestCertificate findByProfessorIdAndInterestId(Long professorId, Long interestId);
	public List<InterestCertificate> findByProfessorId(Long professorId);
}
