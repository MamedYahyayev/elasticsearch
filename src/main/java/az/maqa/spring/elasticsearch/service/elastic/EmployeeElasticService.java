package az.maqa.spring.elasticsearch.service.elastic;

import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeElasticService {
    EmployeeElasticDto createEmployee(EmployeeElasticDto employeeElasticDto);

    List<EmployeeElasticDto> getElasticEmployeeByName(String name);

    List<EmployeeElasticDto> getElasticEmployeeByNameAndSurname(String name, String surname);

    List<EmployeeElasticDto> getElasticEmployeeByBetweenSalary(Double minSalary, Double maxSalary);

    List<EmployeeElasticDto> getElasticEmployeeByNameAndSurnameWithLike(String name, String surname);

    List<EmployeeElasticDto> getElasticEmployeeByGreaterThanSalary(Double amount);

    List<EmployeeElasticDto> getElasticEmployeeBySurnameIgnoreCase(String surname);

    List<EmployeeElasticDto> getElasticEmployeeByDepartmentName(String departmentName);

    Page<EmployeeElasticDto> findAllElasticEmployeeByPage(int reqPage, int reqSize);

    List<EmployeeElasticDto> createAllEmployee(List<EmployeeElasticDto> employeeElasticDtoList);
}
