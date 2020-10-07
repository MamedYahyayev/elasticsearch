package az.maqa.spring.elasticsearch.service.jpa;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployeeByName(String name);

    List<EmployeeDto> getAllEmployeeBySalaryBetween(Double minSalary, Double maxSalary);

    List<EmployeeDto> getAllEmployeesBySurnameAndNameWithLike(String name, String surname);

    List<EmployeeDto> getAllEmployeesBySalaryGreaterThan(Double salary);

    List<EmployeeDto> getEmployeeBySurnameIgnoreCase(String surname);

    Page<EmployeeDto> findAllEmployeeByPage(int page, int size);
}
