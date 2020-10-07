package az.maqa.spring.elasticsearch.controller.elastic;

import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;
import az.maqa.spring.elasticsearch.request.elastic.RequestElasticEmployee;
import az.maqa.spring.elasticsearch.response.elastic.ResponseElasticEmployee;
import az.maqa.spring.elasticsearch.service.elastic.EmployeeElasticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

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
    public ResponseElasticEmployee createEmployee(@RequestBody RequestElasticEmployee requestEmployee) {
        EmployeeElasticDto employeeElasticDto = modelMapper.map(requestEmployee, EmployeeElasticDto.class);
        EmployeeElasticDto savedEmployee = employeeElasticService.createEmployee(employeeElasticDto);
        return modelMapper.map(savedEmployee, ResponseElasticEmployee.class);
    }

    @PostMapping("/saveAll")
    public List<ResponseElasticEmployee> createAllEmployee(@RequestBody List<RequestElasticEmployee> requestElasticEmployeeList) {
        Type employeeElasticDtoListType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();

        List<EmployeeElasticDto> employeeElasticDtoList = modelMapper.map(requestElasticEmployeeList, employeeElasticDtoListType);

        List<EmployeeElasticDto> savedEmployeeElasticDtoList = employeeElasticService.createAllEmployee(employeeElasticDtoList);

        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();

        return modelMapper.map(savedEmployeeElasticDtoList, listType);
    }

    @GetMapping("/findByName/{name}")
    public List<ResponseElasticEmployee> getElasticEmployeeByName(@PathVariable("name") String name) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByName(name);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/fullname")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByNameAndSurname(name, surname);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/salary")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByNameAndSurname(Double minSalary, Double maxSalary) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByBetweenSalary(minSalary, maxSalary);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/fullname/like")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByNameAndSurnameWithLike(String name, String surname) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByNameAndSurnameWithLike(name, surname);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/salary/{amount}")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByNameAndSurname(@PathVariable("amount") Double amount) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByGreaterThanSalary(amount);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/surname/{surname}")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByNameAndSurnameWithLike(@PathVariable("surname") String surname) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeBySurnameIgnoreCase(surname);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping("/findByDepartment/{departmentName}")
    public List<ResponseElasticEmployee> getAllElasticEmployeeByDepartmentName(@PathVariable("departmentName") String departmentName) {
        List<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.getElasticEmployeeByDepartmentName(departmentName);
        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

    @GetMapping
    public Page<ResponseElasticEmployee> getAllElasticEmployeeByPage(String page, String size) {
        int reqPage = 0, reqSize = 5;
        if (size != null && page != null) {
            reqPage = Integer.parseInt(page);
            reqSize = Integer.parseInt(size);
        }
        Page<EmployeeElasticDto> employeeElasticDtoList = employeeElasticService.findAllElasticEmployeeByPage(reqPage, reqSize);
        Type listType = new TypeToken<Page<ResponseElasticEmployee>>() {
        }.getType();
        return modelMapper.map(employeeElasticDtoList, listType);
    }

}
