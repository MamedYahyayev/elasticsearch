package az.maqa.spring.elasticsearch.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressES {

    @Field(name = "country", type = FieldType.Text)
    private String country;

    @Field(name = "city", type = FieldType.Text)
    private String city;

    @Field(name = "street", type = FieldType.Text)
    private String street;

    @Field(name = "zip_code", type = FieldType.Text)
    private String zipCode;
}
