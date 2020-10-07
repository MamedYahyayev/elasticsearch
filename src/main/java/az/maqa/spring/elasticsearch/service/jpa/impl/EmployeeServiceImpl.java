package az.maqa.spring.elasticsearch.service.jpa.impl;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.model.entity.Department;
import az.maqa.spring.elasticsearch.model.entity.Employee;
import az.maqa.spring.elasticsearch.repository.jpa.DepartmentRepository;
import az.maqa.spring.elasticsearch.repository.jpa.EmployeeRepository;
import az.maqa.spring.elasticsearch.service.jpa.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Department department = departmentRepository.getById(employee.getDepartment().getId());
        employee.setDepartment(department);
        Employee savedEmployee = repository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployeeByName(String name) {
        List<Employee> employees = repository.findAllByName(name);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, listType);
    }

    @Override
    public List<EmployeeDto> getAllEmployeeBySalaryBetween(Double minSalary, Double maxSalary) {
        List<Employee> employees = repository.findAllBySalaryBetweenOrderBySalaryAsc(minSalary, maxSalary);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, listType);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesBySurnameAndNameWithLike(String name, String surname) {
        List<Employee> employees = repository.findByNameIsContainingOrSurnameIsContaining(name, surname);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, listType);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesBySalaryGreaterThan(Double salary) {
        List<Employee> employees = repository.findAllBySalaryGreaterThan(salary);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, listType);
    }

    @Override
    public List<EmployeeDto> getEmployeeBySurnameIgnoreCase(String surname) {
        List<Employee> employees = repository.findBySurnameIgnoreCase(surname);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, listType);
    }

    @Override
    public Page<EmployeeDto> findAllEmployeeByPage(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Employee> employeePage = repository.findAll(pageRequest);
        Type listType = new TypeToken<Page<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employeePage, listType);
    }
}
