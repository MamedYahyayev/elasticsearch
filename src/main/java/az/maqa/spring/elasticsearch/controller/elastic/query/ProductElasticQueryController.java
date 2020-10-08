package az.maqa.spring.elasticsearch.controller.elastic.query;

import az.maqa.spring.elasticsearch.dto.elastic.query.ProductElasticQueryDto;
import az.maqa.spring.elasticsearch.request.elastic.query.RequestElasticQueryProduct;
import az.maqa.spring.elasticsearch.response.elastic.query.ResponseElasticQueryProduct;
import az.maqa.spring.elasticsearch.service.elastic.query.ProductElasticQueryService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/elastic/query/product")
public class ProductElasticQueryController {

    private final ProductElasticQueryService productElasticQueryService;
    private final ModelMapper modelMapper;

    public ProductElasticQueryController(ProductElasticQueryService productElasticQueryService, ModelMapper modelMapper) {
        this.productElasticQueryService = productElasticQueryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public IndexResponse createProduct(@RequestBody RequestElasticQueryProduct queryProduct) throws IOException {
        return productElasticQueryService.createProduct(queryProduct);
    }

    @GetMapping("/{id}")
    public ResponseElasticQueryProduct getProductByProductId(@PathVariable String id) throws IOException {
        ProductElasticQueryDto productDto = productElasticQueryService.getProductByProductId(id);
        return modelMapper.map(productDto, ResponseElasticQueryProduct.class);
    }

    @PutMapping("/{id}")
    public UpdateResponse updateProductByProductId(@PathVariable String id, @RequestBody RequestElasticQueryProduct queryProduct) throws IOException {
        return productElasticQueryService.updateProductByProductId(id, queryProduct);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deleteProductByProductId(@PathVariable String id) throws IOException {
        return productElasticQueryService.deleteProductByProductId(id);
    }



   /* @PostMapping("/saveAll")
    public List<ResponseElasticEmployee> createAllEmployee(@RequestBody List<RequestElasticEmployee> requestElasticEmployeeList) {
        Type employeeElasticDtoListType = new TypeToken<List<EmployeeElasticDto>>() {
        }.getType();

        List<EmployeeElasticDto> employeeElasticDtoList = modelMapper.map(requestElasticEmployeeList, employeeElasticDtoListType);

        List<EmployeeElasticDto> savedEmployeeElasticDtoList = employeeElasticService.createAllEmployee(employeeElasticDtoList);

        Type listType = new TypeToken<List<ResponseElasticEmployee>>() {
        }.getType();

        return modelMapper.map(savedEmployeeElasticDtoList, listType);
    }*/

    /*@GetMapping("/findByName/{name}")
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
    }*/
}
