package az.maqa.spring.elasticsearch.repository.elastic;

import az.maqa.spring.elasticsearch.model.document.EmployeeES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EmployeeElasticRepository extends ElasticsearchRepository<EmployeeES, String> {

    List<EmployeeES> findByName(String name);

    List<EmployeeES> findByNameAndSurname(String name, String surname);

    List<EmployeeES> findAllBySalaryBetweenOrderBySalaryAsc(Double minSalary, Double maxSalary);

    List<EmployeeES> findByNameIsContainingOrSurnameIsContaining(String name, String surname);

    List<EmployeeES> findAllBySalaryGreaterThan(Double salary);

    List<EmployeeES> findBySurnameIgnoreCase(String surname);

    List<EmployeeES> findAllByDepartment_DepartmentName(String departmentName);

    @Override
    Page<EmployeeES> findAll(Pageable pageable);

    @Override
    <S extends EmployeeES> Iterable<S> saveAll(Iterable<S> iterable);
}
