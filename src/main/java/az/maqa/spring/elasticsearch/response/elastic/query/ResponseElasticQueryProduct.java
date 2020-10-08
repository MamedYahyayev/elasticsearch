package az.maqa.spring.elasticsearch.response.elastic.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseElasticQueryProduct {

    private String id;

    private String productName;

    private String productCode;

    private Double amount;
}
