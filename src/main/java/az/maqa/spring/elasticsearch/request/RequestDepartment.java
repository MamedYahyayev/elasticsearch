package az.maqa.spring.elasticsearch.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDepartment {
    private String departmentName;
    private String departmentCode;
}
