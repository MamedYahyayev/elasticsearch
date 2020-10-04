package az.maqa.spring.elasticsearch.service.elastic;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;

import java.util.List;

public interface EmployeeElasticService {
    EmployeeElasticDto createEmployee(EmployeeElasticDto employeeElasticDto);

    List<EmployeeDto> getAllEmployee(String search);
}
