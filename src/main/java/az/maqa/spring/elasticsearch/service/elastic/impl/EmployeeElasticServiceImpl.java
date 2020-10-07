package az.maqa.spring.elasticsearch.service.elastic.impl;

import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;
import az.maqa.spring.elasticsearch.model.document.EmployeeES;
import az.maqa.spring.elasticsearch.repository.elastic.EmployeeElasticRepository;
import az.maqa.spring.elasticsearch.service.elastic.EmployeeElasticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmployeeElasticServiceImpl implements EmployeeElasticService {

    private final EmployeeElasticRepository repository;
    private final ModelMapper modelMapper;

    public EmployeeElasticServiceImpl(EmployeeElasticRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeElasticDto createEmployee(EmployeeElasticDto employeeElasticDto) {
        EmployeeES employeeES = modelMapper.map(employeeElasticDto, EmployeeES.class);
        EmployeeES savedEmployeeES = repository.save(employeeES);
        return modelMapper.map(savedEmployeeES, EmployeeElasticDto.class);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByName(String name) {
        List<EmployeeES> employeeESList = repository.findByName(name);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeES> employeeESList = repository.findByNameAndSurname(name, surname);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByBetweenSalary(Double minSalary, Double maxSalary) {
        List<EmployeeES> employeeESList = repository.findAllBySalaryBetweenOrderBySalaryAsc(minSalary, maxSalary);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByNameAndSurnameWithLike(String name, String surname) {
        List<EmployeeES> employeeESList = repository.findByNameIsContainingOrSurnameIsContaining(name, surname);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByGreaterThanSalary(Double amount) {
        List<EmployeeES> employeeESList = repository.findAllBySalaryGreaterThan(amount);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeBySurnameIgnoreCase(String surname) {
        List<EmployeeES> employeeESList = repository.findBySurnameIgnoreCase(surname);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> getElasticEmployeeByDepartmentName(String departmentName) {
        List<EmployeeES> employeeESList = repository.findAllByDepartment_DepartmentName(departmentName);
        Type listType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public Page<EmployeeElasticDto> findAllElasticEmployeeByPage(int reqPage, int reqSize) {
        Pageable pageable = PageRequest.of(reqPage, reqSize);
        Page<EmployeeES> employeeESList = repository.findAll(pageable);
        Type listType = new TypeToken<Page<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(employeeESList, listType);
    }

    @Override
    public List<EmployeeElasticDto> createAllEmployee(List<EmployeeElasticDto> employeeElasticDtoList) {
        Type listType = new TypeToken<List<EmployeeES>>() {
        }.getType();
        List<EmployeeES> employeeEsList = modelMapper.map(employeeElasticDtoList, listType);
        List<EmployeeES> savedEmployeeEsList = (List<EmployeeES>) repository.saveAll(employeeEsList);
        Type returnListType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();
        return modelMapper.map(savedEmployeeEsList, returnListType);
    }

}
