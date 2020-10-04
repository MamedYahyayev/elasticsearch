package az.maqa.spring.elasticsearch.repository.elastic;

import az.maqa.spring.elasticsearch.model.document.EmployeeES;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EmployeeElasticRepository extends ElasticsearchRepository<EmployeeES, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<EmployeeES> getByCustomQuery(String search);

}
