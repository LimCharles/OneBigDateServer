package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	public Professor findByEmployeeId(int employeeId);
	public List<Professor> findByIsSingle(Boolean isSingle);
}
