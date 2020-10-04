package az.maqa.spring.elasticsearch.service.jpa.impl;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.model.entity.Employee;
import az.maqa.spring.elasticsearch.repository.jpa.EmployeeRepository;
import az.maqa.spring.elasticsearch.service.jpa.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = repository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }


}
