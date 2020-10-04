package az.maqa.spring.elasticsearch.response.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseElasticDepartment {
    private String departmentName;
    private String departmentCode;
}
