package az.maqa.spring.elasticsearch.service.jpa;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

}
