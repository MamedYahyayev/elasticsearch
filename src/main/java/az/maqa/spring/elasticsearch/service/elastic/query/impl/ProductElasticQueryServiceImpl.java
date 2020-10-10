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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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

    @Override
    public List<ProductElasticQueryDto> getAllProducts() throws IOException {
        // Request
        SearchRequest request = getSearchRequestForAllProducts(0, 20);

        // Response
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for All Products {}", response.toString());

        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> getAllProductsByName(String name) throws IOException {
        SearchResponse response = findProductsByProperties("productName", name);
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> findAllProductsByProductCode(String code) throws IOException {
        SearchResponse response = findProductsByProperties("productCode", code);
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> findAllProductsByAmount(String amount) throws IOException {
        SearchResponse response = findProductsByProperties("amount", amount);
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> searchMultiField(RequestElasticQueryProduct product) throws IOException {
        SearchRequest request = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = multiFieldQuery(product);

        sourceBuilder.query(queryBuilder);
        request.source(sourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for MultiSearch {}", response.toString());

        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> findProductByBetweenSalary(Double minSalary, Double maxSalary) throws IOException {
        SearchResponse response = rangeAmount(minSalary, maxSalary);
        log.info("Search Response for Between Salary {}", response.toString());
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> findProductByGreaterThanSalary(Double salary) throws IOException {
        SearchResponse response = rangeAmount(salary, null);
        log.info("Search Response for Greater Than Salary {}", response.toString());
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> findProductByLessThanSalary(Double salary) throws IOException {
        SearchResponse response = rangeAmount(null, salary);
        log.info("Search Response for Less Than Salary {}", response.toString());
        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> orderBySalaryAsc() throws IOException {
        // Request
        SearchRequest request = orderBySalaryRequest(SortOrder.ASC);

        // Response
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for Sorting Amount {}", response.toString());

        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> orderBySalaryDesc() throws IOException {
        // Request
        SearchRequest request = orderBySalaryRequest(SortOrder.DESC);

        // Response
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for Sorting Amount {}", response.toString());

        return searchResponseToDto(response);
    }

    @Override
    public List<ProductElasticQueryDto> getAllProductsByPageable(int reqPage, int reqSize) throws IOException {
        // Request
        SearchRequest request = getSearchRequestForAllProducts(reqPage, reqSize);

        // Response
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for All Products with Page {}", response.toString());

        return searchResponseToDto(response);
    }


    // Additional Methods
    private SearchRequest orderBySalaryRequest(SortOrder orderType) {
        SearchRequest request = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SortBuilder sortBuilder = SortBuilders.fieldSort("amount");
        sortBuilder.order(orderType);
        sourceBuilder.from(0);
        sourceBuilder.size(25);
        sourceBuilder.sort(sortBuilder);
        request.source(sourceBuilder);
        return request;
    }

    private BoolQueryBuilder multiFieldQuery(RequestElasticQueryProduct product) {
        return QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("productName", product.getProductName()))
                .must(QueryBuilders.matchQuery("productCode", product.getProductCode()))
                .must(QueryBuilders.matchQuery("amount", product.getAmount()));
    }

    private SearchRequest getSearchRequestForAllProducts(int from, int size) {
        SearchRequest request = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.from(from);
        sourceBuilder.size(size);
        request.source(sourceBuilder);
        return request;
    }

    private SearchResponse rangeAmount(Double from, Double to) throws IOException {
        SearchRequest request = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder queryBuilder = new RangeQueryBuilder("amount");
        queryBuilder.from(from);
        queryBuilder.to(to);
        queryBuilder.includeLower(false);

        sourceBuilder.query(queryBuilder);
        request.source(sourceBuilder);

        return client.search(request, RequestOptions.DEFAULT);
    }

    private SearchResponse findProductsByProperties(String key, String value) throws IOException {
        // Request
        SearchRequest request = new SearchRequest("product");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery(key, value));

        sourceBuilder.query(queryBuilder);
        request.source(sourceBuilder);

        // Response
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        log.info("Search Response for Products {}", response.toString());
        return response;
    }

    private List<ProductElasticQueryDto> searchResponseToDto(SearchResponse response) {
        List<ProductElasticQueryDto> productDtoList = new ArrayList<>();
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();

        for (SearchHit hit : searchHits) {
            ProductElasticQueryDto productDto = new ProductElasticQueryDto();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            log.info("Product Properties {}", sourceAsMap.toString());
            productDto.setId(hit.getId());
            productDto.setAmount((Double) sourceAsMap.get("amount"));
            productDto.setProductName(sourceAsMap.get("productName").toString());
            productDto.setProductCode(sourceAsMap.get("productCode").toString());
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    private void responseToDto(ProductElasticQueryDto productDto, GetRequest getRequest) throws IOException {
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        productDto.setId(response.getId());
        productDto.setAmount((Double) response.getSource().get("amount"));
        productDto.setProductCode(response.getSource().get("productCode").toString());
        productDto.setProductName(response.getSource().get("productName").toString());
        log.info(String.valueOf(response));
    }
}
