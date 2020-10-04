package az.maqa.spring.elasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String id;
    private String name;
    private String surname;
    private Double salary;
    private Date birthDate;
    private DepartmentDto department;
    private AddressDto address;
}
