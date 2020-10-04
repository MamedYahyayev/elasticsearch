package az.maqa.spring.elasticsearch.repository.jpa;

import az.maqa.spring.elasticsearch.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
