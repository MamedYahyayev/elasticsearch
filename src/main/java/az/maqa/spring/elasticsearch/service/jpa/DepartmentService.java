package az.maqa.spring.elasticsearch.service.jpa;

import az.maqa.spring.elasticsearch.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
}
