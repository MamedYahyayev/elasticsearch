package az.maqa.spring.elasticsearch.model.document;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentES {

    @Field(name = "department_name", type = FieldType.Text)
    private String departmentName;

    @Field(name = "department_code", type = FieldType.Text)
    private String departmentCode;

}
