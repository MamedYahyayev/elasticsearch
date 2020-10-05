package az.maqa.spring.elasticsearch.service.jpa.impl;

import az.maqa.spring.elasticsearch.dto.DepartmentDto;
import az.maqa.spring.elasticsearch.model.entity.Department;
import az.maqa.spring.elasticsearch.repository.jpa.DepartmentRepository;
import az.maqa.spring.elasticsearch.service.jpa.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }
}
