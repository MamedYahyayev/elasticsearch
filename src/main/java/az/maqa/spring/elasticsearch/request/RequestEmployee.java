package az.maqa.spring.elasticsearch.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmployee {
    private String name;

    private String surname;

    private Double salary;

    private Date birthDate;

    private RequestDepartment department;

    private RequestAddress address;
}
