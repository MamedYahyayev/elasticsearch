package az.maqa.spring.elasticsearch.service.elastic.impl;

import az.maqa.spring.elasticsearch.dto.EmployeeDto;
import az.maqa.spring.elasticsearch.dto.elastic.EmployeeElasticDto;
import az.maqa.spring.elasticsearch.model.document.EmployeeES;
import az.maqa.spring.elasticsearch.repository.elastic.EmployeeElasticRepository;
import az.maqa.spring.elasticsearch.service.elastic.EmployeeElasticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public List<EmployeeDto> getAllEmployee(String search) {
        List<EmployeeES> employeeES = repository.getByCustomQuery(search);
        Type listType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employeeES, listType);
    }
}
