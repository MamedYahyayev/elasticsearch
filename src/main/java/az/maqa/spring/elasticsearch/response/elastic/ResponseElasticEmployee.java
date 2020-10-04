package az.maqa.spring.elasticsearch.response.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseElasticEmployee {
    private String id;
    private String name;
    private String surname;
    private Double salary;
    private Date birthDate;
    private ResponseElasticDepartment department;
    private ResponseElasticAddress address;
}
