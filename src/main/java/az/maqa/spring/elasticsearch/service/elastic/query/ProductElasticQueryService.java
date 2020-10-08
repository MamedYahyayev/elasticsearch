package az.maqa.spring.elasticsearch.service.elastic.query;

import az.maqa.spring.elasticsearch.dto.elastic.query.ProductElasticQueryDto;
import az.maqa.spring.elasticsearch.request.elastic.query.RequestElasticQueryProduct;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;

public interface ProductElasticQueryService {
    IndexResponse createProduct(RequestElasticQueryProduct queryProduct) throws IOException;

    ProductElasticQueryDto getProductByProductId(String id) throws IOException;

    UpdateResponse updateProductByProductId(String id, RequestElasticQueryProduct queryProduct) throws IOException;

    DeleteResponse deleteProductByProductId(String id) throws IOException;
}
