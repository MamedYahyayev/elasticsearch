package az.maqa.spring.elasticsearch.service.elastic.query;

import az.maqa.spring.elasticsearch.dto.elastic.query.ProductElasticQueryDto;
import az.maqa.spring.elasticsearch.request.elastic.query.RequestElasticQueryProduct;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.List;

public interface ProductElasticQueryService {
    IndexResponse createProduct(RequestElasticQueryProduct queryProduct) throws IOException;

    ProductElasticQueryDto getProductByProductId(String id) throws IOException;

    UpdateResponse updateProductByProductId(String id, RequestElasticQueryProduct queryProduct) throws IOException;

    DeleteResponse deleteProductByProductId(String id) throws IOException;

    List<ProductElasticQueryDto> getAllProducts() throws IOException;

    List<ProductElasticQueryDto> getAllProductsByName(String name) throws IOException;

    List<ProductElasticQueryDto> findAllProductsByProductCode(String code) throws IOException;

    List<ProductElasticQueryDto> findAllProductsByAmount(String amount) throws IOException;

    List<ProductElasticQueryDto> searchMultiField(RequestElasticQueryProduct product) throws IOException;

    List<ProductElasticQueryDto> findProductByBetweenSalary(Double minSalary, Double maxSalary) throws IOException;

    List<ProductElasticQueryDto> findProductByGreaterThanSalary(Double salary) throws IOException;

    List<ProductElasticQueryDto> findProductByLessThanSalary(Double salary) throws IOException;

    List<ProductElasticQueryDto> orderBySalaryAsc() throws IOException;

    List<ProductElasticQueryDto> getAllProductsByPageable(int reqPage, int reqSize) throws IOException;

    List<ProductElasticQueryDto> orderBySalaryDesc() throws IOException;

}
