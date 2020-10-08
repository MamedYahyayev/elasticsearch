package az.maqa.spring.elasticsearch.model.document.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "product", type = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    @Field(name = "product_name", type = FieldType.Text)
    private String productName;

    @Field(name = "product_code", type = FieldType.Text)
    private String productCode;

    @Field(name = "amount", type = FieldType.Double)
    private Double amount;

}
