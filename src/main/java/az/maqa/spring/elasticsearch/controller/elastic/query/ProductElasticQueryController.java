package az.maqa.spring.elasticsearch.controller.elastic.query;

import az.maqa.spring.elasticsearch.dto.elastic.query.ProductElasticQueryDto;
import az.maqa.spring.elasticsearch.request.elastic.query.RequestElasticQueryProduct;
import az.maqa.spring.elasticsearch.response.elastic.query.ResponseElasticQueryProduct;
import az.maqa.spring.elasticsearch.service.elastic.query.ProductElasticQueryService;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

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

    @GetMapping
    public List<ResponseElasticQueryProduct> findAllProducts() throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.getAllProducts();
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping(value = "/findByName/{name}")
    public List<ResponseElasticQueryProduct> findAllProductsByProductName(@PathVariable("name") String name) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.getAllProductsByName(name);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping(value = "/findByProductCode/{code}")
    public List<ResponseElasticQueryProduct> findAllProductsByProductCode(@PathVariable("code") String code) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.findAllProductsByProductCode(code);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping(value = "/findByAmount/{amount}")
    public List<ResponseElasticQueryProduct> findAllProductsByAmount(@PathVariable("amount") String amount) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.findAllProductsByAmount(amount);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @PostMapping(value = "/search")
    public List<ResponseElasticQueryProduct> searchMultiField(@RequestBody RequestElasticQueryProduct product) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.searchMultiField(product);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/betweenSalary")
    public List<ResponseElasticQueryProduct> findProductByBetweenSalary(Double minSalary, Double maxSalary) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.findProductByBetweenSalary(minSalary, maxSalary);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/greaterThanSalary/{salary}")
    public List<ResponseElasticQueryProduct> findProductByGreaterThanSalary(@PathVariable Double salary) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.findProductByGreaterThanSalary(salary);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/lessThanSalary/{salary}")
    public List<ResponseElasticQueryProduct> findProductByLessThanSalary(@PathVariable Double salary) throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.findProductByLessThanSalary(salary);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/orderBySalaryAsc")
    public List<ResponseElasticQueryProduct> orderBySalary() throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.orderBySalaryAsc();
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/orderBySalaryDesc")
    public List<ResponseElasticQueryProduct> orderBySalaryDesc() throws IOException {
        List<ProductElasticQueryDto> allProducts = productElasticQueryService.orderBySalaryDesc();
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(allProducts, listType);
    }

    @GetMapping("/pageRequest")
    public List<ResponseElasticQueryProduct> getAllProductsByPageable(String page, String size) throws IOException {
        int reqPage = 0, reqSize = 5;
        if (size != null && page != null) {
            reqPage = Integer.parseInt(page);
            reqSize = Integer.parseInt(size);
        }
        List<ProductElasticQueryDto> productDtoList = productElasticQueryService.getAllProductsByPageable(reqPage, reqSize);
        Type listType = new TypeToken<List<ResponseElasticQueryProduct>>() {
        }.getType();
        return modelMapper.map(productDtoList, listType);
    }
}
