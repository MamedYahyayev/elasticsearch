package az.maqa.spring.elasticsearch.dto.elastic.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductElasticQueryDto {
    private String id;

    private String productName;

    private String productCode;

    private Double amount;
}
