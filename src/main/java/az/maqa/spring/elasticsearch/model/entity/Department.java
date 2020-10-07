package az.maqa.spring.elasticsearch.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", length = 30)
    private String departmentName;

    @Column(name = "department_code", length = 10)
    private String departmentCode;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private List<Employee> employees;

}
