package az.maqa.spring.elasticsearch.controller.jpa;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.request.RequestEmployee;
import az.maqa.spring.elasticsearch.response.ResponseEmployee;
import az.maqa.spring.elasticsearch.service.jpa.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEmployee createEmployee(@RequestBody RequestEmployee requestEmployee) {
        EmployeeDto employeeDto = modelMapper.map(requestEmployee, EmployeeDto.class);
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return modelMapper.map(savedEmployee, ResponseEmployee.class);
    }

    @GetMapping("/search")
    public List<ResponseEmployee> getAllEmployeesByName(String name) {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeeByName(name);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }

    @GetMapping("/between")
    public List<ResponseEmployee> getAllEmployeesBySalaryBetween(Double minSalary, Double maxSalary) {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeeBySalaryBetween(minSalary, maxSalary);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }

    @GetMapping("/fullname")
    public List<ResponseEmployee> getAllEmployeesBySurnameAndNameWithLike(String name, String surname) {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeesBySurnameAndNameWithLike(name, surname);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }

    @GetMapping("/salary")
    public List<ResponseEmployee> getAllEmployeesBySalaryGreaterThan(Double salary) {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployeesBySalaryGreaterThan(salary);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }

    @GetMapping("/searchSurname")
    public List<ResponseEmployee> getEmployeeBySurnameIgnoreCase(String surname) {
        List<EmployeeDto> employeeDtoList = employeeService.getEmployeeBySurnameIgnoreCase(surname);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }
}
