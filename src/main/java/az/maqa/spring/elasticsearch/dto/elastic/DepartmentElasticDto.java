package az.maqa.spring.elasticsearch.dto.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentElasticDto {
    private String departmentName;
    private String departmentCode;
}
