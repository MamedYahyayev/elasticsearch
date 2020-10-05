package az.maqa.spring.elasticsearch.controller.jpa;

import az.maqa.spring.elasticsearch.dto.DepartmentDto;
import az.maqa.spring.elasticsearch.request.RequestDepartment;
import az.maqa.spring.elasticsearch.response.ResponseDepartment;
import az.maqa.spring.elasticsearch.service.jpa.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;


    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseDepartment createDepartment(@RequestBody RequestDepartment requestDepartment) {
        DepartmentDto departmentDto = modelMapper.map(requestDepartment, DepartmentDto.class);
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return modelMapper.map(savedDepartment, ResponseDepartment.class);
    }

}
