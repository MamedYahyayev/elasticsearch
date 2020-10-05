package az.maqa.spring.elasticsearch.repository.jpa;

import az.maqa.spring.elasticsearch.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department getById(Long id);
}
