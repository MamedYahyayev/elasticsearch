package az.maqa.spring.elasticsearch.repository.jpa;

import az.maqa.spring.elasticsearch.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findAllByName(String name);

    List<Employee> findAllBySalaryBetweenOrderBySalaryAsc(Double minSalary, Double maxSalary);

    List<Employee> findByNameIsContainingOrSurnameIsContaining(String name, String surname);

    List<Employee> findAllBySalaryGreaterThan(Double salary);

    List<Employee> findBySurnameIgnoreCase(String surname);

    @Override
    Page<Employee> findAll(Pageable pageable);
}
