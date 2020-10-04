package az.maqa.spring.elasticsearch.dto.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeElasticDto {
    private String id;
    private String name;
    private String surname;
    private Double salary;
    private Date birthDate;
    private DepartmentElasticDto department;
    private AddressElasticDto address;
}
