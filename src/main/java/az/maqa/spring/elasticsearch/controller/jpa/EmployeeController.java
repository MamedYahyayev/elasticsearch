package az.maqa.spring.elasticsearch.controller.jpa;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.request.RequestEmployee;
import az.maqa.spring.elasticsearch.response.ResponseEmployee;
import az.maqa.spring.elasticsearch.service.jpa.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;


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
}
