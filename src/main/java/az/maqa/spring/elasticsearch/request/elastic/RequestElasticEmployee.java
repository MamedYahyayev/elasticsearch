package az.maqa.spring.elasticsearch.request.elastic;

import az.maqa.spring.elasticsearch.request.RequestAddress;
import az.maqa.spring.elasticsearch.request.RequestDepartment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestElasticEmployee {
    private String name;

    private String surname;

    private Double salary;

    private Date birthDate;

    private RequestDepartment department;

    private RequestAddress address;
}
