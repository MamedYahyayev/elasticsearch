package az.maqa.spring.elasticsearch.repository.jpa;

import az.maqa.spring.elasticsearch.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByName(String name);

    List<Employee> findAllBySalaryBetweenOrderBySalaryAsc(Double minSalary, Double maxSalary);

    List<Employee> findByNameIsContainingOrSurnameIsContaining(String name, String surname);

    List<Employee> findAllBySalaryGreaterThan(Double salary);

    List<Employee> findBySurnameIgnoreCase(String surname);

}
