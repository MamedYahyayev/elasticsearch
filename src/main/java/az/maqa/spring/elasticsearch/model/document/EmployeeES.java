package az.maqa.spring.elasticsearch.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "employees", type = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeES {

    @Id
    private String id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "surname", type = FieldType.Text)
    private String surname;

    @Field(name = "salary", type = FieldType.Double)
    private Double salary;

    @Field(name = "birth_date", type = FieldType.Date, format = DateFormat.basic_date)
    private Date birthDate;

    @Field(name = "address", type = FieldType.Nested)
    private AddressES address;

    @Field(name = "department", type = FieldType.Nested)
    private DepartmentES department;
}
