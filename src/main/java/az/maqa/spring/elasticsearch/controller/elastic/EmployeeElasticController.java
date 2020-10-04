package az.maqa.spring.elasticsearch.controller.elastic;

import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;
import az.maqa.spring.elasticsearch.request.RequestEmployee;
import az.maqa.spring.elasticsearch.response.elastic.ResponseElasticEmployee;
import az.maqa.spring.elasticsearch.service.elastic.EmployeeElasticService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elastic/employee")
public class EmployeeElasticController {

    private final EmployeeElasticService employeeElasticService;
    private final ModelMapper modelMapper;

    public EmployeeElasticController(EmployeeElasticService employeeElasticService, ModelMapper modelMapper) {
        this.employeeElasticService = employeeElasticService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseElasticEmployee createEmployee(@RequestBody RequestEmployee requestEmployee) {
        EmployeeElasticDto employeeElasticDto = modelMapper.map(requestEmployee, EmployeeElasticDto.class);
        EmployeeElasticDto savedEmployee = employeeElasticService.createEmployee(employeeElasticDto);
        return modelMapper.map(savedEmployee, ResponseElasticEmployee.class);
    }

    /*@GetMapping("/{search}")
    public List<ResponseEmployee> getEmployee(@PathVariable("search") String search) {
        List<EmployeeDto> employeeDtoList = employeeElasticService.getAllEmployee(search);
        Type listType = new TypeToken<List<ResponseEmployee>>() {
        }.getType();
        return modelMapper.map(employeeDtoList, listType);
    }*/


}
