package az.maqa.spring.elasticsearch.service.elastic.query.impl;

import az.maqa.spring.elasticsearch.dto.elastic.query.ProductElasticQueryDto;
import az.maqa.spring.elasticsearch.request.elastic.query.RequestElasticQueryProduct;
import az.maqa.spring.elasticsearch.service.elastic.query.ProductElasticQueryService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class ProductElasticQueryServiceImpl implements ProductElasticQueryService {

    private final RestHighLevelClient client;

    public ProductElasticQueryServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public IndexResponse createProduct(RequestElasticQueryProduct queryProduct) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("productName", queryProduct.getProductName());
        jsonMap.put("productCode", queryProduct.getProductCode());
        jsonMap.put("amount", queryProduct.getAmount());
        IndexRequest request = new IndexRequest("product").id(UUID.randomUUID().toString()).source(jsonMap);
        return client.index(request, RequestOptions.DEFAULT);
    }

    @Override
    public ProductElasticQueryDto getProductByProductId(String id) throws IOException {
        ProductElasticQueryDto productDto = new ProductElasticQueryDto();
        GetRequest getRequest = new GetRequest("product", id);
        responseToDto(productDto, getRequest);
        return productDto;
    }

    @Override
    public UpdateResponse updateProductByProductId(String id, RequestElasticQueryProduct queryProduct) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("product", id).doc("amount", queryProduct.getAmount(),
                "productCode", queryProduct.getProductCode(),
                "productName", queryProduct.getProductName());
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        log.info(response.toString());
        return response;
    }

    @Override
    public DeleteResponse deleteProductByProductId(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("product", id);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        log.info("Delete Response: {} ", response.toString());
        return response;
    }

    private void responseToDto(ProductElasticQueryDto productDto, GetRequest getRequest) throws IOException {
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        productDto.setId(response.getId());
        productDto.setAmount((Double) response.getSource().get("amount"));
        productDto.setProductCode((String) response.getSource().get("productCode"));
        productDto.setProductName((String) response.getSource().get("productName"));
        log.info(String.valueOf(response));
    }
}
