package az.maqa.spring.elasticsearch.request.elastic.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestElasticQueryProduct {

    private String productName;

    private String productCode;

    private Double amount;
}
