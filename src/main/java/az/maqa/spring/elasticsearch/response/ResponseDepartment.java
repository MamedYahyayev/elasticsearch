package az.maqa.spring.elasticsearch.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDepartment {
    private String id;
    private String departmentName;
    private String departmentCode;
}
