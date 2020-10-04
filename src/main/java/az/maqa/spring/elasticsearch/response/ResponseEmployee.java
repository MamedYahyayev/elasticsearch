package az.maqa.spring.elasticsearch.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
    private String id;
    private String name;
    private String surname;
    private Double salary;
    private Date birthDate;
    private ResponseDepartment department;
    private ResponseAddress address;
}
